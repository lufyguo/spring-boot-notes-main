package com.ziorye.service;

import com.ziorye.bean.City;
import com.ziorye.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ziorye
 */
@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;

    public City findById(Long id) {
        return cityMapper.findById(id);
    }

    public void insertCity(City city) {
        cityMapper.insertCity(city);
    }
}
