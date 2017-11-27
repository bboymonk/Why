package com.wjb.controller;

import com.wjb.model.Country;
import com.wjb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/11/27.
 */
@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @ResponseBody
    @GetMapping("get")
    public Country getCountry(Integer id,String name){
        return countryService.country(id,name);
    }



}
