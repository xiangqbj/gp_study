package com.gupaoedu.vip.service.impl;

import com.gupaoedu.vip.service.IHelloService;
import com.gupaoedu.vip.service.RpcService;

@RpcService(value = IHelloService.class, version = "1.0")
public class HelloServiceImpl implements IHelloService {

    public String sayHello(String content) {
        System.out.println("v1.0 HelloServiceImpl.sayHello() is called");

        return "v1.0 Hello " + content;
    }
}
