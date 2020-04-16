package com.lean.lumen.mapper;

import com.lean.lumen.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) " +
            "values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question order by gmt_create desc")
    List<Question> list(@Param("pageNum") int pageNum,
                        @Param("pageSize") int pageSize);

    @Select("select * from question where creator=#{creator} order by gmt_create desc")
    List<Question> listById(@Param("creator") Integer creator,
                            @Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize);


    @Select("select * from question where id = #{id}")
    Question getQuestionById(@Param("id") Integer id);


    @Update("update question set title = #{title}, description=#{description}, gmt_modified=now(), tag=#{tag}" +
            "where id = #{id}")
    void updateQuestionById(Question question);

    @Update("update question set view_count = view_count+1 " +
            "where id = #{id}")
    void updateQuestionIncById(@Param("id") Integer id);

    @Update("update question set comment_count = comment_count+1 " +
            "where id = #{id}")
    void updateQuestionCommentCountById(@Param("id") Integer id);
}
