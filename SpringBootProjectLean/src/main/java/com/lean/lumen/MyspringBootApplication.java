package com.lean.lumen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lean.lumen.mapper")
public class MyspringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringBootApplication.class);
    }
}
