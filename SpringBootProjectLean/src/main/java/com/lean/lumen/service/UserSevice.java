package com.lean.lumen.service;

import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserSevice {

    @Resource
    UserMapper userMapper;

    public User createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            userMapper.insert(user);
        } else {
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(user);
        }

        return dbUser;
    }
}
