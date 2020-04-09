# 学习Spring Boot第三天

### SpringBoot集成Mybatis
在pom文件中加入集成的引用
```xml
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```
在application.yaml中加入Mybatis需要的配置
```yaml
mybatis:
  type-aliases-package: com.lean.lumen
  mapper-locations: classpath:mapper/*.xml
```
`type-aliases-package`表示要扫描的包
`Mapper-locations`表示要扫描的xml

以下是数据库sql
```sql
-- CREATE TABLE `uesr` (
-- 	`id` INT(11) not null auto_increment,
-- 	`username` VARCHAR(50) default null,
-- 	`password` VARCHAR(50) DEFAULT null,
-- 	`name` VARCHAR(50) DEFAULT null,
-- 	PRIMARY key(`id`)
-- )ENGINE=INNODB auto_increment=10 DEFAULT CHARSET=utf8;

INSERT into `uesr` VALUES ('1', 'zhangsan','123','张三');
INSERT into `uesr` VALUES ('2', 'lisi','123','李四');
```


### SpringBoot集成redis

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```
在application.yaml中加入需要的配置
```yaml
spring:
  redis:
    hots: 127.0.0.1
    port: 6379
```