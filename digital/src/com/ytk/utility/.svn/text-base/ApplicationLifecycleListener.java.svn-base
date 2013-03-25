package com.ytk.utility;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ApplicationLifecycleListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent event) {
       
        ServletContext context =  event.getServletContext();
        ApplicationContextUtil.setContext(WebApplicationContextUtils.getRequiredWebApplicationContext(context));       
    }
   
    public void contextDestroyed(ServletContextEvent event) {
       
    }
}
