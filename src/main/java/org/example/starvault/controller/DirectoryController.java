package org.example.starvault.controller;

import org.example.starvault.params.DirectoryParam;
import org.example.starvault.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import stark.dataworks.boot.web.ServiceResponse;

@Controller
@RequestMapping("/directory")
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
}
