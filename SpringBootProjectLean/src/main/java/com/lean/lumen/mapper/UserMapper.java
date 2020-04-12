package com.lean.lumen.mapper;

import com.lean.lumen.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "values(#{name}, #{accountId}, #{token}, #{gmt_Created}, #{gmt_modified}, #{avatar_url})")
    void insert(User user);//通过注解方式直接写sql, 如果方法参数是对象, 则sql中可以直接#属性名使用

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token")String token);//如果参数不是对象, 则需要使用@Param注解来传递

    @Select("select * from user where id = #{id}")
    User findById(@Param("id")Integer id);
}
