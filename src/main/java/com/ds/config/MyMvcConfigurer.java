package com.ds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

//    @Resource
//    private ObjectMapper objectMapper;

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.removeIf(o -> o instanceof MappingJackson2HttpMessageConverter);
//        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//    }


}
