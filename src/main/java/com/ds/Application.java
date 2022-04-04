package com.ds;
/**
 * @author dongsheng
 */
import java.util.Arrays;
import javax.jms.ConnectionFactory;
import javax.jms.Topic;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ServletComponentScan//扫描到自定义的filter和servlet
@MapperScan("com.ds.mapper")
//@EnableScheduling//开启定时任务
//@EnableAsync//开启异步任务
//@EnableJms//开启jms消息队列工具
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize(DataSize.ofKilobytes(1024)); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofKilobytes(1024));
        return factory.createMultipartConfig();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
//                System.out.println(beanName);
            }

        };
    }

    /**
     * activemq
     * @return
     */
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("video.topic");
    }

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("common.queue");
    }
    //给topic定义独立的jmsListenerContainerTopic
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }
}
