package com.ziorye.mapper;

import com.ziorye.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author ziorye
 */
public interface CityMapper {
    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
    City findById(long id);

    /*@Insert("INSERT INTO city (name, state, country) VALUES(#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")*/
    void insertCity(City city);
}
