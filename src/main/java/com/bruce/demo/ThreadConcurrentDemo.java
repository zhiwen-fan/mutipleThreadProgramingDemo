package com.bruce.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadConcurrentDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final Test test = new Test();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> futures = new ArrayList<Future<?>>();
        for (int i = 0; i < 10; i++) {
            Future<?> future = executorService.submit(new Runnable() {
                public void run() {
                    test.execute();
                }
            });
            futures.add(future);
        }

        for (Future future: futures) {
            future.get();
        }
        System.out.println(test.getCount());
    }

    static class Test{
        private int count;

        public synchronized void execute() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
