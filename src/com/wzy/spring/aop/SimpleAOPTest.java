package com.wzy.spring.aop;

import org.junit.Test;

public class SimpleAOPTest {
    @Test
    public void getProxy() throws Exception {
        // 1. ����һ�� MethodInvocation ʵ����
        MethodInvocation logTask = new MethodInvocation() {
			
			@Override
			public void invoke() throws Throwable {
				System.out.println("log task start");
			}
		}; 
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        
        // 2. ����һ�� Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);
        
        // 3. ΪĿ��������ɴ���
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);
        
        helloServiceImplProxy.sayHelloWorld();
    }
}