package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.starvault.entities.User;
import org.example.starvault.entities.Version;

@Mapper
public interface UserMapper
{
    void addUser(User user);
    User getUserByEmail(String email);
    boolean ifContainsEmail(String email);
    void resetPassword(User user);
    String getBucketByUserId(Long userId);

    void shiftUserBucket(Version version);
}
