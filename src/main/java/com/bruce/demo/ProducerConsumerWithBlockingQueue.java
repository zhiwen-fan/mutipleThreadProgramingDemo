package com.bruce.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerWithBlockingQueue {

    public static void main(String[] args) {
        final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(5);
        final AtomicInteger stopMark = new AtomicInteger();
        Thread  producer = new Thread(new Runnable() {
            private int id  = 1;
            public void run() {
                try {
                    while (stopMark.get() ==0) {
                        String msg = "msg_" + id++;
                        blockingQueue.put(msg);
                        System.out.println("produce msg : " + msg);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            public void run() {
                try {
                    while (stopMark.get() ==0) {
                        String msg = blockingQueue.take();
                        System.out.println("consume msg : " + msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopMark.getAndIncrement();
    }
}
