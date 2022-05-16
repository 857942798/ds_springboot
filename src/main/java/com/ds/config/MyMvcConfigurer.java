package com.ds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: dongsheng
 * @CreateTime: 2020-11-12
 * @Description:
 */
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
    /**
     * 静态资源映射
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/tyhmeleaf/**").addResourceLocations("classpath:/META-INF/resources/templates/");
            }
        };
    }



}
