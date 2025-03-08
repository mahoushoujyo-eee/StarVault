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

    List<DirectoryAndFileParam> getFileParamsByDirectoryId(DirectoryParam directory);

    List<File> getFilesByDirectoryId(DirectoryParam directory);

    void deleteFile(File file);

    List<Long> getFileIdsByDirectoryId(DirectoryParam directoryParam);
}
