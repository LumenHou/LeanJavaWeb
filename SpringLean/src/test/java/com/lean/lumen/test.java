package com.lean.lumen;

import com.lean.lumen.beans.User;
import com.lean.lumen.config.ConfigClassTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

public class test {

    @Resource
    User user;

    @Test
    public void test01(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigClassTest.class);
//        String[] names = context.getBeanDefinitionNames();
//
//        Arrays.stream(names).forEach(System.out::println);
        System.out.println(user);
    }

}
