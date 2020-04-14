//package com.lean.lumen.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lean.lumen.MyspringBootApplication;
//import com.lean.lumen.dao.User;
//import com.lean.lumen.mapper.UserMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MyspringBootApplication.class)
//public class redisTest {
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Test
//    public void test() throws JsonProcessingException {
//        //
//        String s = redisTemplate.boundValueOps("user.findAll").get();
//        if (null == s){
//            List<User> users = userMapper.queryUserList();
//            ObjectMapper objectMapper = new ObjectMapper();
//            s = objectMapper.writeValueAsString(users);
//
//            redisTemplate.boundValueOps("user.findAll").set(s);
//            System.out.println("get users from database");
//        }else {
//            System.out.println("get users from redis");
//        }
//
//        System.out.println(s);
//    }
//}
