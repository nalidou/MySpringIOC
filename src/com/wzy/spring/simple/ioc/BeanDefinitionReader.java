package com.wzy.spring.simple.ioc;

import java.io.FileNotFoundException;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws FileNotFoundException, Exception;

}
