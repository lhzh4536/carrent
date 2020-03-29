package com.lhzh.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //获取servletContext
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("cxt",servletContext.getContextPath());
        System.err.println("---------Servlet容器创建成功 ctx被放到ServletContext作用域-------");
    }
}
