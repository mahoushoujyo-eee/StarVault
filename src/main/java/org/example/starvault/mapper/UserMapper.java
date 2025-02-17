package org.example.starvault.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.starvault.entities.User;

@Mapper
public interface UserMapper
{
    void addUser(User user);
    User getUserByEmail(String email);
    boolean ifContainsEmail(String email);

    void resetPassword(User user);
}
