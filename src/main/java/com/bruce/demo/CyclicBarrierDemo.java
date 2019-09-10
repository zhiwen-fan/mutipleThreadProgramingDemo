package com.bruce.demo;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for ( int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Worker(cyclicBarrier));
            thread.setName("Thread-" + i);
            thread.start();
        }
    }

    static class Worker implements Runnable {

        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始执行。。。");
            try {
                Thread.sleep(1000 * new Random().nextInt(3));
                System.out.println(Thread.currentThread().getName() + " 开始等待其他线程");
                System.out.println("###########################");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 继续执行，执行时间: " + new Date());
                Thread.sleep(2000);
                System.out.println("###########################");
                System.out.println(Thread.currentThread().getName() + " 执行完成");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
