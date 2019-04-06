package com.wzy.spring.simple.ioc.xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wzy.spring.simple.ioc.BeanDefinition;
import com.wzy.spring.simple.ioc.BeanDefinitionReader;
import com.wzy.spring.simple.ioc.BeanReference;
import com.wzy.spring.simple.ioc.PropertyValue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by code4wt on 17/8/2.
 * 此类用于解析xml文件，根据xml的文件属性，取出一个个bean，用BeanDefinition进行bean的属性描述，最后把解析好的bean全部存入registry
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        registry = new HashMap<>();
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);//根据文件地址把xml内数据转化为Element，进行解析
    }

    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);//解析单个<bean></bean>
            }
        }
    }

    private void parseBeanDefinition(Element ele) throws Exception {
		//这里的ele就是单个<bean></bean>
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition(); //BeanDefinition封装的属性是对bean的描述
        beanDefinition.setBeanClassName(className); //BeanClassName是全类名，同时根据全类名对bean反射得到bean的Class对象，但还未实例化
        processProperty(ele, beanDefinition); // 开始解析<property name="" value=""/>的集合
        registry.put(name, beanDefinition); //把bean的描述beanDefinition存入Map
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property"); //取出所有的property，挨个解析
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));//将property的name和value存入beanDefinition的属性列表里
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);//若是引用，则直接将引用存入属性列表中
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
