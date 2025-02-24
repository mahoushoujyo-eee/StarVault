package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.starvault.entities.Directory;
import org.example.starvault.params.DirectoryParam;

import java.util.List;

@Mapper
public interface DirectoryMapper
{
    public int createDirectory(DirectoryParam directory);
    public void initializeUserDirectory(Directory directory);

    public boolean ifContainsDirectory(DirectoryParam directory);

    public List<Directory> getDirectoriesByUserId(DirectoryParam directory);
}
