package org.example.starvault.service;

import io.minio.errors.*;
import org.example.starvault.entities.User;
import org.example.starvault.entities.Version;
import org.example.starvault.mapper.UserMapper;
import org.example.starvault.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stark.dataworks.boot.autoconfig.minio.EasyMinio;
import stark.dataworks.boot.web.ServiceResponse;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DirectoryService directoryService;

    @Autowired
    EasyMinio easyMinio;

    @Value("${dataworks.easy-minio.file-bucket-name}")
    String bucketPrefixName;

    private final String DEFAULT_AVATAR_URL = "http://localhost:9001/api/v1/download-shared-object/aHR0cDovLzEyNy4wLjAuMTo5MDAwL3VzZXItYXZhdGFyL2RlZmF1bHRfYXZhdGFyLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPTNJQ01LWVA5TkFLVUZEOTBVSzg1JTJGMjAyNTAyMTIlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwMjEyVDA3MTI0NFomWC1BbXotRXhwaXJlcz00MzIwMCZYLUFtei1TZWN1cml0eS1Ub2tlbj1leUpoYkdjaU9pSklVelV4TWlJc0luUjVjQ0k2SWtwWFZDSjkuZXlKaFkyTmxjM05MWlhraU9pSXpTVU5OUzFsUU9VNUJTMVZHUkRrd1ZVczROU0lzSW1WNGNDSTZNVGN6T1RNM05qSTBOQ3dpY0dGeVpXNTBJam9pYldsdWFXOWhaRzFwYmlKOS4tQzRmNHZ4ZURfUDZ4UVN0d1hNWmVmaXJqOHdpQ1ZxY2dCNTNYNTZPaklEWHJONFQ5Z2FKYjRsTGkzN3dXbG9uS0pWaXZZN1NkQ1RSbFRSakdOeU9BZyZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QmdmVyc2lvbklkPW51bGwmWC1BbXotU2lnbmF0dXJlPThjNDA3YzFlYTMxZGNkOTNkZGY5YjFiNTMxZjVlOWRjMGNhYTc5NDNiOGRkOTEyMGE3MTg0Y2FmYWVkMmI4Y2E";

    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse<Boolean> addUser(User user) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException
    {
        user.setAvatar(DEFAULT_AVATAR_URL);
        user.setPassword(MD5.encryptToMD5(user.getPassword()));
        if(userMapper.ifContainsEmail(user.getEmail()))
        {
            return ServiceResponse.buildErrorResponse(-100, "邮箱已被注册！");
        }
        userMapper.addUser(user);
        Long userId = user.getId();

        System.out.println(userId);
        easyMinio.createBucket(bucketPrefixName + userId.toString());
        directoryService.initializeUserDirectory(user);

        return ServiceResponse.buildSuccessResponse(true);
    }

    public ServiceResponse<User> login(User user)
    {
        User storedUser = userMapper.getUserByEmail(user.getEmail());
        if(storedUser == null || storedUser.getPassword() == null)
            return ServiceResponse.buildErrorResponse(-100, "邮箱不存在！");
        else if(storedUser.getPassword().equals(MD5.encryptToMD5(user.getPassword())))
            return ServiceResponse.buildSuccessResponse(storedUser);
        else
            return ServiceResponse.buildErrorResponse(-101, "密码错误！");
    }

    public ServiceResponse<Boolean> resetPassword(User user)
    {
        if(user.getEmail() == null || !userMapper.ifContainsEmail(user.getEmail()))
            return ServiceResponse.buildErrorResponse(-100, "邮箱不存在！");
        else if(user.getPassword() == null)
            return ServiceResponse.buildErrorResponse(-101, "密码不能为空！");
        else
        {
            user.setPassword(MD5.encryptToMD5(user.getPassword()));
            userMapper.resetPassword(user);

            ServiceResponse<Boolean> response = new ServiceResponse<>();
            response.setData(true);
            response.setMessage("密码重置成功！");
            return response;
        }
    }

    public String getUserBucketName(Long userId)
    {
        return  userMapper.getBucketByUserId(userId);
    }

    public void shiftUserBucket(Version version)
    {
        userMapper.shiftUserBucket(version);
    }
}
