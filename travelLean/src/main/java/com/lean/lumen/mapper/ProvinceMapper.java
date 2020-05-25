package com.lean.lumen.mapper;

import com.lean.lumen.bean.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProvinceMapper {

    @Select("select * from t_province order by id limit #{start}, #{rows}")
    List<Province> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    @Select("select count(*) from t_province")
    Integer countProvince();
}
