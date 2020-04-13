package com.lean.lumen.mapper;

import com.lean.lumen.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) " +
            "values (#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question order by gmt_create desc")
    List<Question> list(@Param("pageNum") int pageNum,
                        @Param("pageSize") int pageSize);

    @Select("select * from question where creator=#{creator} order by gmt_create desc")
    List<Question> listById(@Param("creator") Integer creator,
                        @Param("pageNum") int pageNum,
                        @Param("pageSize") int pageSize);
}
