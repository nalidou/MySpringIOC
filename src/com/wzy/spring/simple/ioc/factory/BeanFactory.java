package com.wzy.spring.simple.ioc.factory;

public interface BeanFactory {
	
    Object getBean(String beanId) throws Exception;
}
