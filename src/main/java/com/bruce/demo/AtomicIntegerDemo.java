package com.bruce.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) {
        final AtomicInteger count = new AtomicInteger();

        for (int i = 0; i< 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int i=0; i< 1000; i++) {
                        count.getAndIncrement();
                    }
                }
            });
            thread.start();
        }


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("expect count result is: " + count.get());
    }
}
