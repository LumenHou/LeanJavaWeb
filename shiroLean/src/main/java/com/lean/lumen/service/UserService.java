package com.lean.lumen.service;

import com.lean.lumen.bean.User;
import com.lean.lumen.mapper.userMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Value("${shiroLean.length}")
    Integer length;

    @Value("${shiroLean.salt}")
    String salt;

    @Autowired
    userMapper userMapper;

    public void registerUser(User user) {
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, length);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);

        userMapper.saveUser(user);
    }
}
