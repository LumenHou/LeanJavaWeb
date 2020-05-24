package com.lean.lumen.service;

import com.lean.lumen.bean.User;
import com.lean.lumen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void registerUser(User user) {
        User userDao = findUser(user.getUsername());
        if (userDao == null) {
            userMapper.save(user);
        } else {
            throw new RuntimeException("user exits");
        }
    }

    public User findUser(String username) {
        return userMapper.findByUsername(username);
    }
}
