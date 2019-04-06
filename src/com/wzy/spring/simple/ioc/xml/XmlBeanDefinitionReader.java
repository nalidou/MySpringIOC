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
 * �������ڽ���xml�ļ�������xml���ļ����ԣ�ȡ��һ����bean����BeanDefinition����bean���������������ѽ����õ�beanȫ������registry
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
        parseBeanDefinitions(root);//�����ļ���ַ��xml������ת��ΪElement�����н���
    }

    private void parseBeanDefinitions(Element root) throws Exception {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);//��������<bean></bean>
            }
        }
    }

    private void parseBeanDefinition(Element ele) throws Exception {
		//�����ele���ǵ���<bean></bean>
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition(); //BeanDefinition��װ�������Ƕ�bean������
        beanDefinition.setBeanClassName(className); //BeanClassName��ȫ������ͬʱ����ȫ������bean����õ�bean��Class���󣬵���δʵ����
        processProperty(ele, beanDefinition); // ��ʼ����<property name="" value=""/>�ļ���
        registry.put(name, beanDefinition); //��bean������beanDefinition����Map
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNodes = ele.getElementsByTagName("property"); //ȡ�����е�property����������
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));//��property��name��value����beanDefinition�������б���
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);//�������ã���ֱ�ӽ����ô��������б���
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
