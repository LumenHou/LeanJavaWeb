package com.lean.lumen.mapper;

import com.lean.lumen.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(username, password, email) values(#{username}, #{password}, #{email})")
    void save(User user);

    @Select("select * from t_user where username = #{username}")
    User findByUsername(@Param("username") String username);
}
