package org.example.starvault.service;

import org.example.starvault.entities.Directory;
import org.example.starvault.entities.User;
import org.example.starvault.mapper.DirectoryMapper;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stark.dataworks.boot.web.ServiceResponse;

import java.util.List;

@Service
public class DirectoryService
{
    @Autowired
    private DirectoryMapper directoryMapper;

    @Autowired
    private FileService fileService;
    public ServiceResponse<DirectoryParam> createDirectory(DirectoryParam directory)
    {
        directoryMapper.createDirectory(directory);
        return ServiceResponse.buildSuccessResponse(directory);
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
     * 获取用户目录
     * @param  directory DirectoryParam
     * @return List<Directory>
     */
    public ServiceResponse<List<DirectoryAndFileParam>> getUserDirectory(DirectoryParam directory)
    {
        List<DirectoryAndFileParam> directories = directoryMapper.getDirectoriesByUserId(directory);
        directories.forEach(directoryAndFileParam ->
        {
            directoryAndFileParam.setType("文件夹");
        });
        directories.addAll(fileService.getFiles(directory));
        directories.forEach(directoryAndFileParam ->
        {
            if(directoryAndFileParam.getType() == null)
            {
                directoryAndFileParam.setType("文件");
            }
        });
        return ServiceResponse.buildSuccessResponse(directories);
    }

    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse<Boolean> deleteDirectory(DirectoryParam directory)
    {
        directoryMapper.deleteDirectory(directory);
        return ServiceResponse.buildSuccessResponse(true);
    }

    public ServiceResponse<Boolean> renameDirectory(DirectoryParam directory)
    {
        if (directoryMapper.ifContainsDirectory(directory))
        {
            directoryMapper.renameDirectory(directory);
            return ServiceResponse.buildSuccessResponse(true);
        }
        else
        {
            return ServiceResponse.buildErrorResponse(-100, "目录不存在");
        }
    }

    public ServiceResponse<Directory> getRootDirectory(Long userId)
    {
        directoryMapper.getRootDirectory(userId);
        return ServiceResponse.buildSuccessResponse(directoryMapper.getRootDirectory(userId));
    }
}
