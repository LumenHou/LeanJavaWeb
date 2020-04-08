# SpringBoot 第一天

1. Spring的优缺点
    1. AOC / IOP
    2. 缺点, 配置太过于繁琐, 虽然在2.5和后续的3版本中加入了注解来减轻配置, 但是仍然配置很多
2. SpringBoot的特点
    1. 解决Spring的缺点, 采用约定大于配置的思想
    2. 更快的入门, 开箱即用, 无需繁琐的xml配置
    3. 提供了大型项目中常见的非功能特性, 比如`嵌入式服务器`, `安全`, `健康检测`, 外部配置
3. SpringBoot主要功能
    1. 起步依赖, 将某种功能的依赖打包至一起,  并提供一些默认的功能
    2. 自动配置
    
编写了第一个hello boot程序

Spring Boot程序必须继承以下maven
```xml    
<parent>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-parent</artifactId>
     <version>2.0.1.RELEASE</version>
</parent>
```

`@SpringBootApplication` 注解标记是一个SpringBoot引导类
会自动扫描当前package及以下所有package的所有类, 并注册为bean
