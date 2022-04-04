package com.ds.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @description: 抽象系统启动工具
 * @author: yotas
 * @create: 2020-05-14 16:03
 **/
public abstract class AbstractWebApplicationInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        //注册Servlets
        initServlets(context);
        //注册Servlet
        initListeners(context);
    }

    private void initServlets(ServletContext context) {
        //注册Spring DispatcherServlet容器
        ServletRegistration.Dynamic dispatchServelt = context.addServlet("springDispatcherServlet", DispatcherServlet.class);
        dispatchServelt.addMapping("/");
        dispatchServelt.setInitParameter("contextConfigLocation", "classpath:spring/application.xml");
        dispatchServelt.setLoadOnStartup(1);

        this.doInitServlets(context);
    }


    private void initListeners(ServletContext context) {
        this.doInitListeners(context);
    }

    abstract void doInitServlets(ServletContext context);

    abstract void doInitFilters(ServletContext context);

    abstract void doInitListeners(ServletContext context);
}
