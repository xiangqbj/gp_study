package com.gupaoedu.vip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public RpcProxyClient rpcProxyClient() {
        return new RpcProxyClient();
    }
}
