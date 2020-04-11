package com.lean.lumen;

import com.lean.lumen.beans.User;
import com.lean.lumen.config.ConfigClassTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringMainLean {

    public static void main(String[] args) {
//        ApplicationContext ac = new FileSystemXmlApplicationContext("C:\\Users\\lumen\\IdeaProjects\\LeanJavaWeb\\SpringLean\\src\\main\\resources\\beans.xml");
//        User user = (User) ac.getBean("user");
//        System.out.println(user);

        ApplicationContext ac = new AnnotationConfigApplicationContext(ConfigClassTest.class);
        System.out.println(ac.getBean(User.class));

//        System.out.printlintln(user);
    }
}
