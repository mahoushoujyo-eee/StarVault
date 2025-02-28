package org.example.starvault.controller;

import org.example.starvault.entities.Directory;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;
import org.example.starvault.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;

import java.util.List;

@RestController
@RequestMapping("/directory")
@LogArgumentsAndResponse
public class DirectoryController
{
    @Autowired
    DirectoryService directoryService;

    @PostMapping("/create")
    public ServiceResponse<DirectoryParam> createDirectory(@RequestBody DirectoryParam directory)
    {
        return directoryService.createDirectory(directory);
    }

    // initializeUserDirectory
    @PostMapping("/initialize")
    public ServiceResponse<List<DirectoryAndFileParam>> getUserDirectory(@RequestBody DirectoryParam directory)
    {
        return directoryService.getUserDirectory(directory);
    }

    @GetMapping("/root/{id}")
    public ServiceResponse<Directory> getRootDirectory(@PathVariable("id") Long userId)
    {
        return directoryService.getRootDirectory(userId);
    }

    @PostMapping("/delete")
    public ServiceResponse<Boolean> deleteDirectory(@RequestBody DirectoryParam directory)
    {
        return directoryService.deleteDirectory(directory);
    }

    @PostMapping("/rename")
    public ServiceResponse<Boolean> renameDirectory(@RequestBody DirectoryParam directory)
    {
        return directoryService.renameDirectory(directory);
    }
}
