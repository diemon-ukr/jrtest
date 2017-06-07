package com.diemon.helper;

import com.diemon.service.ServiceDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zkplus.spring.SpringUtil;

/**
 * Created by Diemon on 6/6/2017.
 */
public class AppUtil {
    // ONLY USED FOR CALLS IN UI.WEB
    private static ApplicationContext context;
    private static ServiceDao service;


    // ONLY USED FOR CALLS IN UI.WEB
    private static ServiceDao webService;

    public static ServiceDao getWebService() {
        if (webService == null) {
            webService = (ServiceDao) SpringUtil.getBean("serviceDao");
        }
        return webService;
    }


    private static ApplicationContext getSpringContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        }
        return context;
    }

    public static ServiceDao getService() {
        if (service == null) {
            service = (ServiceDao) getSpringContext().getBean("serviceDao");
        }
        return service;
    }
}