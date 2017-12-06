package com.wjb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping("time")
public class TimeController {

    @ResponseBody
    @PostMapping("getDate")
    public String time(String date){
        System.out.println(date);
        return null;
    }


}
