package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.starvault.entities.Directory;
import org.example.starvault.params.DirectoryAndFileParam;
import org.example.starvault.params.DirectoryParam;

import java.util.List;

@Mapper
public interface DirectoryMapper
{
    int createDirectory(DirectoryParam directory);

    void initializeUserDirectory(Directory directory);

    boolean ifContainsDirectory(DirectoryParam directory);

    List<DirectoryAndFileParam> getDirectoriesByUserId(DirectoryParam directory);

    Directory getRootDirectory(Long userId);

    void deleteDirectory(DirectoryParam directory);

    void renameDirectory(DirectoryParam directory);

    List<Long> getDirectoriesByDirectoryId(DirectoryParam directory);

    List<DirectoryParam> getDirectoriesByParentId(DirectoryParam directoryParam);
}
