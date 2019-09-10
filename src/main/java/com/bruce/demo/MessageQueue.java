package com.bruce.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageQueue {
    private List<String> msgQueue = new ArrayList<String>(5);
    private int queueSize = 5;

    private Object lock = new Object();

    public void produce(String msg) throws InterruptedException {
        synchronized (lock) {
            if(isQueueFull()) {
                lock.wait();
            }
            msgQueue.add(msg);
            System.out.println(new Date() + "---produce msg" + msg );
            lock.notifyAll();
        }
    }

    public String consume() throws InterruptedException {
        synchronized (lock) {
            if(isQueueEmpty()) {
                lock.wait();
            }
            String msg = msgQueue.remove(0);
            System.out.println(new Date() + "--consume msg" + msg);
            lock.notifyAll();
            return msg;
        }
    }


    private boolean isQueueFull() {
        return msgQueue.size() == 5;
    }

    private boolean isQueueEmpty() {
        return msgQueue.isEmpty();
    }
}
