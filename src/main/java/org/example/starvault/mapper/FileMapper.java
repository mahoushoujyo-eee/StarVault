package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.starvault.entities.File;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;

import java.util.List;

@Mapper
public interface FileMapper
{
    void addFile(File file);

    //还不能用！
    List<DirectoryAndFileParam> getFilesByDirectoryId(DirectoryParam directory);
}
