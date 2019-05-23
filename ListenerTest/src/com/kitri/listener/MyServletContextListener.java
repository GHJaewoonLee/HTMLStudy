package com.kitri.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	System.out.println("MyServletContextListener Object created");
    }

    
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("contextInitialized method called");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed method called");
    }
}
