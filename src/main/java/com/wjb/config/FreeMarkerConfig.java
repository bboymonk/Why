package com.wjb.config;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


/**
 * Created by Administrator on 2017/11/16.
 */
@Component
public class FreeMarkerConfig {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Bean
    public Configuration getFreeMarkerConfiguration(){
        return freeMarkerConfigurer.getConfiguration();
    }

}
