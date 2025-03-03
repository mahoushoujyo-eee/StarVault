package org.example.starvault.controller;

import io.minio.errors.*;
import org.example.starvault.entities.File;
import org.example.starvault.params.FileChunkParam;
import org.example.starvault.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stark.dataworks.boot.autoconfig.minio.EasyMinio;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/file")
@LogArgumentsAndResponse
public class FileController
{
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ServiceResponse<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("parentId") Long parentId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        return fileService.addFile(file, userId, parentId);
    }

    @PostMapping("/upload-chunks-start")
    public ServiceResponse<String> uploadFileChunksStart(@RequestBody File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        return fileService.uploadFileChunksStart(file);
    }

    @PostMapping("/upload-chunks")
    public ServiceResponse<File> uploadFileChunks(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("parentId") Long parentId, @RequestParam("chunkIndex") Integer chunkIndex, @RequestParam("taskId") String taskId, @RequestParam("name") String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        return fileService.addFile(file, userId, parentId, chunkIndex, taskId, fileName);
    }

    @PostMapping("/upload-chunks-compose")
    public ServiceResponse<File> uploadFileCompose(@RequestParam("name") String name, @RequestParam("taskId") String taskId, @RequestParam Long parentId, @RequestParam("userId") Long userId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        FileChunkParam file = new FileChunkParam();
        file.setName(name);
        file.setTaskId(taskId);
        file.setDirectoryId(parentId);
        file.setUserId(userId);
        return fileService.composeObjects(file);
    }

    @PostMapping("delete")
    public ServiceResponse<Boolean> deleteFile(@RequestBody File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        return fileService.deleteFile(file);
    }
}
