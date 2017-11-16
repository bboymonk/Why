package com.wjb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2017/11/16.
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("test")
    public String test(){
        return "pages/test";
    }

    @GetMapping("hacker")
    public String hacker(){
        return "pages/hacker";
    }


}
