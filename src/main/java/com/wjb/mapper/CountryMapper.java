package com.wjb.mapper;

import com.wjb.model.Country;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */
public interface CountryMapper {

    @Select("call getCountry(#{id})")
    public Country getCountry(@Param("id") Integer id);

    @Select("call country(#{id},#{name})")
    public Country country(@Param("id") Integer id,@Param("name") String name);


    @Select("select name from t_country limit 1")
    public String countrys();


}
