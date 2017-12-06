package com.wjb.service;

import com.wjb.mapper.CountryMapper;
import com.wjb.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */
@Service
public class CountryService {
    @Autowired
    private CountryMapper countryMapper;

    public Country getCountry(Integer id){
        return countryMapper.getCountry(id);
    }

    public Country country(Integer id,String name){
        return countryMapper.country(id,name);
    }


    @Cacheable(value = "country")
    public String countrys(){
        return countryMapper.countrys();
    }


}
