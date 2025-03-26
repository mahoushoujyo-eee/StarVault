package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.starvault.entities.Version;

@Mapper
public interface VersionMapper
{
    boolean ifContainsVersion(Version version);
    void addVersion(Version version);
    void deleteVersion(Version version);
}
