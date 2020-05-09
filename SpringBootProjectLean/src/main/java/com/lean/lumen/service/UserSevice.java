package com.lean.lumen.service;

import com.lean.lumen.mapper.UserMapper;
import com.lean.lumen.model.User;
import com.lean.lumen.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSevice {

    @Resource
    UserMapper userMapper;


    public User findById(Integer id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() == 0) return null;
        return users.get(0);
    }

    public User findByToken(String token) {
        if (token == null || "".equals(token)) {
            return null;
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);

        if (users.size() == 0) return null;
        return users.get(0);
    }


    public User createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        User dbUser = new User();
        if (users.size() == 0) {
            userMapper.insert(user);
        } else {
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());

            userExample.createCriteria()
                    .andIdEqualTo(users.get(0).getId());

            userMapper.updateByExampleSelective(dbUser, userExample);
        }

        return dbUser;
    }
}
