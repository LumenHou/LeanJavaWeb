# SpringBoot 第二天

热部署
maven配置以下引用即可
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```
_Idea需要开启自动编译才可以_

Idea 专业版可以快速创建SpringBoot程序 ~~没钱买, 不想用盗版~~

SpringBoot在运行时会自动加载resources下面application*.pro / application.yml等配置文件, 可以使用这种方式覆盖SpringBoot默认配置


```yaml
person:
  age: 12
  name: 曹优
  sex: Fameal
```
`@ConfigurationProperties(prefix = "person")`
在类中加入这个注解, 会自动将配置文件中`person`中的属性, 赋值给类变量(get/set)
