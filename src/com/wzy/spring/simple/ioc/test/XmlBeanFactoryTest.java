package com.wzy.spring.simple.ioc.test;

import org.junit.Test;

import com.wzy.spring.simple.ioc.factory.BeanFactory;
import com.wzy.spring.simple.ioc.xml.XmlBeanFactory;

public class XmlBeanFactoryTest { 

    @Test
    public void getBean() throws Exception {
        String path = "com/wzy/spring/simple/ioc/test/context.xml";
        		
        BeanFactory factory = new XmlBeanFactory(path);
        Student s = (Student)factory.getBean("student");
        System.out.println(s.toString());

        
    }
}