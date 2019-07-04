package com.gupaoedu.vip.nio;

import java.util.concurrent.TimeUnit;

public class NIODemo {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new NIOServer()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new NIOClient()).start();
    }

}
