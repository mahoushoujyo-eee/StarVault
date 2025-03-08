package org.example.starvault.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.example.starvault.entities.File;
import org.example.starvault.mapper.FileMapper;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;
import org.example.starvault.params.FileChunkParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stark.dataworks.boot.autoconfig.minio.EasyMinio;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@LogArgumentsAndResponse
public class FileService
{
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private EasyMinio easyMinio;

    @Autowired
    private RedisTemplate<String, List<String>> redisTemplate;

    public ServiceResponse<File> addFile(MultipartFile file, Long userId, Long parentId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        File fileObj = new File();
        fileObj.setName(file.getOriginalFilename());
        fileObj.setUserId(userId);
        fileObj.setDirectoryId(parentId);
        fileMapper.addFile(fileObj);

        fileObj.setUrl(easyMinio.uploadFileByStream("user-file" + userId, file.getOriginalFilename() + " " + fileObj.getId(), file.getInputStream()));

        return ServiceResponse.buildSuccessResponse(fileObj);
    }

    public ServiceResponse<File> addFile(MultipartFile file, Long userId, Long parentId, int chunkIndex, String taskId, String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        File fileObj = new File();
        fileObj.setName(fileName + "_" + chunkIndex);
        fileObj.setUserId(userId);
        fileObj.setDirectoryId(parentId);
        fileObj.setUrl(easyMinio.uploadFileByStream("user-file" + userId, fileObj.getName(), file.getInputStream()));
        List<String> fileNameList = redisTemplate.opsForValue().get(taskId);
        fileNameList.add(fileObj.getName());
        redisTemplate.opsForValue().set(taskId, fileNameList);

        return ServiceResponse.buildSuccessResponse(fileObj);
    }

    public List<DirectoryAndFileParam> getFiles(DirectoryParam directory)
    {
        List<DirectoryAndFileParam> files = fileMapper.getFileParamsByDirectoryId(directory);
        return files;
    }

    public ServiceResponse<Boolean> deleteFile(File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String bucketName = "user-file" + file.getUserId();
        String objectName = file.getName() + " " + file.getId();
        easyMinio.deleteObjects(bucketName, java.util.Arrays.asList(objectName));
        fileMapper.deleteFile(file);
        return ServiceResponse.buildSuccessResponse(true);
    }

    public ServiceResponse<File> composeObjects(FileChunkParam file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        List<String> objectNames = redisTemplate.opsForValue().get(file.getTaskId());
        objectNames.sort((x, y) -> Integer.parseInt(x.substring(x.lastIndexOf("_") + 1)) - Integer.parseInt(y.substring(x.lastIndexOf("_") + 1)));

        File fileObj = new File();
        fileObj.setName(file.getName());
        fileObj.setUserId(file.getUserId());
        fileObj.setDirectoryId(file.getDirectoryId());
        fileMapper.addFile(fileObj);

        easyMinio.composeObjects("user-file" + fileObj.getUserId(), fileObj.getName() + " " + fileObj.getId(), objectNames);
        fileObj.setUrl(easyMinio.getObjectUrl("user-file" + fileObj.getUserId(), fileObj.getName() + " " + fileObj.getId()));
        new Thread(() ->
        {
            try
            {
                easyMinio.deleteObjects("user-file" + fileObj.getUserId(), objectNames);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
        return ServiceResponse.buildSuccessResponse(fileObj);
    }

    public ServiceResponse<String> uploadFileChunksStart(File file)
    {
        String taskId = file.getName() + ' ' + file.getUserId() + ' ' + System.currentTimeMillis();
        redisTemplate.opsForValue().set(taskId, java.util.Arrays.asList());
        return ServiceResponse.buildSuccessResponse(taskId);
    }

    public ServiceResponse<File> downloadFile(File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String fileName = file.getName();
        String contentDisposition = "attachment; filename=\"" + URLEncoder.encode(fileName,  StandardCharsets.UTF_8).replace("+", "%20") + "\"";
        String bucketName = "user-file" + file.getUserId();

        String preSignedUrl = easyMinio.getMinioClient().getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(file.getName() + " " + file.getId())
                        .extraQueryParams(Map.of("response-content-disposition", contentDisposition))
                        .expiry(1, TimeUnit.HOURS)
                        .method(Method.GET)
                        .build());

        file.setUrl(preSignedUrl);
        return ServiceResponse.buildSuccessResponse(file);
    }

    public List<Long> getFileIdsByDirectoryId(DirectoryParam directoryParam)
    {
        return fileMapper.getFileIdsByDirectoryId(directoryParam);
    }

    public List<File> getFilesByDirectoryId(DirectoryParam directory)
    {
        return fileMapper.getFilesByDirectoryId(directory);
    }
}
