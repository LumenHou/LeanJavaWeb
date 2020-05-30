package com.lean.lumen.mapper;

import com.lean.lumen.bean.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlaceMapper {

    @Select("select * from t_place where provinceid = #{provinceId} " +
            "limit #{page}, #{rows}")
    List<Place> getPlaceByProvince(@Param("page") Integer page, @Param("rows") Integer rows, @Param("provinceId") Integer provinceId);

    @Select("select count(*) from t_place where provinceid = #{provinceId}")
    Integer getTotalPlace(@Param("provinceId") Integer provinceId);

    @Select("select name from t_province order by id")
    List<String> getAllProvinceName();
}
