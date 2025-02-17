package org.example.starvault.controller;

import org.example.starvault.entities.User;
import org.example.starvault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stark.dataworks.boot.autoconfig.web.LogArgumentsAndResponse;
import stark.dataworks.boot.web.ServiceResponse;

@LogArgumentsAndResponse
@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ServiceResponse<User> login(@RequestBody User user)
    {
        return userService.login(user);
    }

    @PostMapping("/user/register")
    public ServiceResponse<Boolean> register(@RequestBody User user)
    {
        userService.addUser(user);
        return ServiceResponse.buildSuccessResponse(true);
    }

    @PostMapping("/user/reset-password")
    public ServiceResponse resetPassword(@RequestBody User user)
    {
        return userService.resetPassword(user);
    }

    @PostMapping("/user/reset-avatar")
    public ServiceResponse resetAvatar()
    {
        return ServiceResponse.buildSuccessResponse(null);
    }


}
