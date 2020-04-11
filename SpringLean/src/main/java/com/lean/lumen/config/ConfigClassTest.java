package com.lean.lumen.config;

import com.lean.lumen.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration//标记beans配置类
@ComponentScan(value = "com.lean.lumen", useDefaultFilters = false,includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
public class ConfigClassTest {

    @Bean
    public User user(){
        return new User();
    }
}
