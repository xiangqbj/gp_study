package com.gupaoedu.vip.service.impl;

import com.gupaoedu.vip.service.IHelloService;
import com.gupaoedu.vip.service.RpcService;

@RpcService(value = IHelloService.class, version = "2.0")
public class HelloServiceImpl2 implements IHelloService {

    public String sayHello(String content) {
        System.out.println("v2.0 HelloServiceImpl.sayHello() is called");

        return "v2.0 Hello " + content;
    }
}
