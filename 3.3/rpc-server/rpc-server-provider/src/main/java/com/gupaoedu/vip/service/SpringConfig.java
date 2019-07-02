package com.gupaoedu.vip.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.gupaoedu.vip")
public class SpringConfig {

    @Bean
    public RpcProxyServer rpcProxyServer() {
        return new RpcProxyServer(8080);
    }
}
