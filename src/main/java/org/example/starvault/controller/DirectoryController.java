package org.example.starvault.controller;

import org.example.starvault.entities.Directory;
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
    public ServiceResponse<Boolean> createDirectory(@RequestBody DirectoryParam directory)
    {
        System.out.println(directory);
        return directoryService.createDirectory(directory);
    }

    // initializeUserDirectory
    @GetMapping("/initialize/{id}")
    public ServiceResponse<List<Directory>> getUserRootDirectory(@PathVariable("id")String userId)
    {
        return directoryService.getUserRootDirectory(Long.parseLong(userId));
    }
}
