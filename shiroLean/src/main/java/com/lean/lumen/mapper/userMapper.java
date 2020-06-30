package com.lean.lumen.mapper;

import com.lean.lumen.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {

    @Insert("insert into user values (#{id}, #{username}, #{password}, #{salt})")
    void saveUser(User user);

    @Select("select * from user where username = #{username}")
    User findUser(@Param("username") String username);
}
