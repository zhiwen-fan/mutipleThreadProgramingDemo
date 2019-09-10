package com.bruce.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                5,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new DefaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i=0; i< 100; i++) {

            submitTask(threadPoolExecutor);
        }

       // monitor(threadPoolExecutor);
    }



    private static void submitTask(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private AtomicInteger threadNum = new AtomicInteger(0);
        private ThreadGroup group = new ThreadGroup("");

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group,r,"demo-thread-" + threadNum.incrementAndGet());
            return thread;
        }
    }

    private static void monitor(final ThreadPoolExecutor threadPoolExecutor) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("activeCount: " + threadPoolExecutor.getActiveCount() +
                                ", corePoolSize: "  +  threadPoolExecutor.getCorePoolSize() +
                                ", MaximumPoolSize: " + threadPoolExecutor.getMaximumPoolSize() +
                                ", CompletedTaskCount: " + threadPoolExecutor.getCompletedTaskCount() +
                                ", TaskCount: " +  threadPoolExecutor.getTaskCount() +
                                ", queuedSize: " +  threadPoolExecutor.getQueue().size());
                        if(threadPoolExecutor.getActiveCount() == 0) {
                            for (int i=0; i< 10; i++) {

                                submitTask(threadPoolExecutor);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }
}
