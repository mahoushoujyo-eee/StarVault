package org.example.starvault.service;

import org.example.starvault.entities.Directory;
import org.example.starvault.entities.User;
import org.example.starvault.mapper.DirectoryMapper;
import org.example.starvault.params.DirectoryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stark.dataworks.boot.web.ServiceResponse;

import java.util.List;

@Service
public class DirectoryService
{
    @Autowired
    private DirectoryMapper directoryMapper;
    public ServiceResponse<Boolean> createDirectory(DirectoryParam directory)
    {
        directoryMapper.createDirectory(directory);
        return ServiceResponse.buildSuccessResponse(true);
    }
    /**
     * 为刚注册的用户初始化一个总目录
     * @param  user User
     * @return void
     */
    public void initializeUserDirectory(User user)
    {
        Directory directory = new Directory();

        directory.setUserId(user.getId());
        directory.setName("总目录");
        directory.setParentId(-1L);
        directoryMapper.initializeUserDirectory(directory);
    }

    /**
     * 初始化一个登入进网盘的用户目录
     * @param  userId Long
     * @return ServiceResponse<List<Directory>>
     */
    public ServiceResponse<List<Directory>> getUserRootDirectory(Long userId)
    {
        DirectoryParam directory = new DirectoryParam();
        directory.setUserId(userId);
        directory.setParentName("总目录");
        List<Directory> directories = directoryMapper.getDirectoriesByUserId(directory);
        return ServiceResponse.buildSuccessResponse(directories);
    }
}
