package com.toymanagement.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileHandler.initialize(sce.getServletContext());
        System.out.println("FileHandler initialized for application");
    }
}