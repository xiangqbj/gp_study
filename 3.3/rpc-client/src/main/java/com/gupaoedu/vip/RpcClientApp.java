package com.gupaoedu.vip;

import com.gupaoedu.vip.service.IHelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RpcClientApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = applicationContext.getBean(RpcProxyClient.class);
        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
        String hello = helloService.sayHello("Xiang");
        System.out.println(hello);
    }
}
