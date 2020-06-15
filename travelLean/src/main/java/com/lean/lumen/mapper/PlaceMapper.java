package com.lean.lumen.mapper;

import com.lean.lumen.bean.Place;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO `travels`.`t_place` (`name`, `picpath`, `hottime`, `hotticket`, `dimticket`, `placedes`, `provinceid`) " +
            "VALUES (#{name}, #{picpath}, #{hottime}, #{hotticket}, #{dimticket}, #{placedes}, #{provinceid})")
    void save(Place place);

    @Delete("delete t_place where id = #{id}")
    void delete(@Param("id") Integer id);

    @Select("select * from t_place where id = #{id}")
    Place find(@Param("id") Integer id);

    @Update("UPDATE `travels`.`t_place` SET `name`=#{name}, `picpath`=#{picpath}, `hottime`= #{hottime}, `hotticket`=#{hotticket}, `dimticket`=#{dimticket}, `placedes`=#{placedes}, `provinceid`=#{provinceid} " +
            "WHERE (`id`=#{id});")
    void update(Place place);
}
