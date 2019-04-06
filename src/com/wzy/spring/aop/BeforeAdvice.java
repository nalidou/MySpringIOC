package com.wzy.spring.aop;

import java.lang.reflect.Method;

public class BeforeAdvice implements Advice {
	
    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // ��Ŀ�귽��ִ��ǰ����֪ͨ
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }

}