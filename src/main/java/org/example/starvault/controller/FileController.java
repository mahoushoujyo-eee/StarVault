package org.example.starvault.controller;

import io.minio.errors.*;
import org.example.starvault.entities.File;
import org.example.starvault.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EasyMinio easyMinio;

    @PostMapping("/upload")
    public ServiceResponse<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId, @RequestParam("parentId") Long parentId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        String url = easyMinio.uploadFile("user-file" + userId, file);
        File fileObj = new File();
        fileObj.setName(file.getOriginalFilename());
        fileObj.setUrl(url);
        fileObj.setUserId(userId);
        fileObj.setDirectoryId(parentId);

        fileService.addFile(fileObj);
        return ServiceResponse.buildSuccessResponse(fileObj);
    }
}
