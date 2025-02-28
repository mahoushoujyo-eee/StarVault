package org.example.starvault.service;

import io.minio.errors.*;
import org.example.starvault.entities.File;
import org.example.starvault.mapper.FileMapper;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stark.dataworks.boot.autoconfig.minio.EasyMinio;
import stark.dataworks.boot.web.ServiceResponse;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class FileService
{
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private EasyMinio easyMinio;

    public void addFile(File file)
    {
        fileMapper.addFile(file);
    }

    public List<DirectoryAndFileParam> getFiles(DirectoryParam directory)
    {
        return fileMapper.getFilesByDirectoryId(directory);
    }

    public ServiceResponse<Boolean> deleteFile(File file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String bucketName = "user-file" + file.getUserId();
        String objectName = file.getName();
        easyMinio.deleteObjects(bucketName, java.util.Arrays.asList(objectName));
        fileMapper.deleteFile(file);
        return ServiceResponse.buildSuccessResponse(true);
    }
}
