package com.bruce.demo;

public class ProducerAndConsumerModelDemo {
    public static void main(String[] args) throws InterruptedException {

        final MessageQueue queue = new MessageQueue();
        Thread producer = new Thread(new Runnable() {
            private int msgId = 1;
            public void run() {
                while (true) {
                    try {
                        long sleepTime = 1000;
                        queue.produce("msg" + msgId++ );
                        if(msgId == 6) {
                            sleepTime = 20000;
                        }
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                       queue.consume();
                       Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producer.start();
        consumer.start();


    }
}
