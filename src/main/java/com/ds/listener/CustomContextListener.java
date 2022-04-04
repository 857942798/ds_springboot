package com.ds.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 可以做一些程序的初始化和销毁动作
 */
@WebListener
public class CustomContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("======contextInitialized========");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("======contextDestroyed========");
		
	}

}
