package com.lean.lumen.mapper;

import com.lean.lumen.bean.Province;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProvinceMapper {

    @Select("select * from t_province order by id limit #{start}, #{rows}")
    List<Province> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    @Select("select count(*) from t_province")
    Integer countProvince();

    @Insert("insert into t_province (name, tags, placecounts) values(#{name}, #{tags}, #{placecounts})")
    void save(Province province);

    @Delete("delete from t_province where id = #{id}")
    void delete(@Param("id") Integer id);

    @Select("select * from t_province where id = #{id}")
    Province findProvince(@Param("id") Integer id);

    @Update("update t_province set name = #{name}, tags=#{tags}, placecounts=#{placecounts} " +
            "where id = #{id}")
    void updateProvinceById(Province province);
}
