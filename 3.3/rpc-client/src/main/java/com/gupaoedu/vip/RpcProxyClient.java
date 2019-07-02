package com.gupaoedu.vip;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    public <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}
