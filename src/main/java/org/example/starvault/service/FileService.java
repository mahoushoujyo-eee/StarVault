package org.example.starvault.service;

import org.example.starvault.entities.File;
import org.example.starvault.mapper.FileMapper;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService
{
    @Autowired
    private FileMapper fileMapper;

    public void addFile(File file)
    {
        fileMapper.addFile(file);
    }

    public List<DirectoryAndFileParam> getFiles(DirectoryParam directory)
    {
        return fileMapper.getFilesByDirectoryId(directory);
    }
}
