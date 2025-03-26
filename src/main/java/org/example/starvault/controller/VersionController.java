package org.example.starvault.controller;

import org.example.starvault.entities.Version;
import org.example.starvault.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stark.dataworks.boot.web.ServiceResponse;

@RestController
@RequestMapping("/version")
public class VersionController
{
    @Autowired
    private VersionService versionService;

    @PostMapping("/shift")
    public ServiceResponse<Boolean> shiftVersion(@RequestBody Version version)
    {
        return versionService.shiftVersion(version);
    }

    @PostMapping("/add")
    public ServiceResponse<Boolean> addVersion(@RequestBody Version version)
    {
        return versionService.addVersion(version);
    }

    @PostMapping("/delete")
    public ServiceResponse<Boolean> deleteVersion(@RequestBody Version version)
    {
        return versionService.deleteVersion(version);
    }
}
