package com.gupaoedu.vip.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcProxyServer implements ApplicationContextAware, InitializingBean {

    private int port;

    private Map<String, Object> handlerMap = new HashMap<>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    public RpcProxyServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port, 128);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new SocketHandler(socket, handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void afterPropertiesSet() throws Exception {
        start();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Object serviceBean : serviceBeanMap.values()) {
            RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
            String serviceName = rpcService.value().getName();
            String version = rpcService.version();
            if (!StringUtils.isEmpty(version)) {
                serviceName += "-" + version;
            }
            handlerMap.put(serviceName, serviceBean);
        }
    }
}
