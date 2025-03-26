package org.example.starvault.service;

import org.example.starvault.entities.Version;
import org.example.starvault.mapper.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stark.dataworks.boot.web.ServiceResponse;

import java.util.Date;

@Service
public class VersionService
{
    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private UserService userService;

    public ServiceResponse<Boolean> addVersion(Version version)
    {
        versionMapper.addVersion(version);
        version.setDate(new Date());
        return ServiceResponse.buildSuccessResponse(true);
    }

    public ServiceResponse<Boolean> shiftVersion(Version version)
    {
        if(!versionMapper.ifContainsVersion(version))
            return ServiceResponse.buildErrorResponse(-100, "版本不存在！");

        userService.shiftUserBucket(version);
        return ServiceResponse.buildSuccessResponse(true);
    }

    public ServiceResponse<Boolean> deleteVersion(Version version)
    {
        if(!versionMapper.ifContainsVersion(version))
            return ServiceResponse.buildErrorResponse(-100, "版本不存在！");

        versionMapper.deleteVersion(version);
        return ServiceResponse.buildSuccessResponse(true);
    }
}
