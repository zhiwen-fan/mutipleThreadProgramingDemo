package com.bruce.demo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        final Semaphore parkingCount = new Semaphore(5);

        for (int i = 0; i< 10; i++) {
            parkingCar(parkingCount);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("####### wait some time #######");

        for (int i = 0; i< 4; i++) {
            parkingCar(parkingCount);
        }
    }

    private static void parkingCar(final Semaphore parkeingCount) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    if(parkeingCount.tryAcquire()){
                        System.out.println("有可用停车位,进站停车");

                        //模拟停车时间
                        Thread.sleep(2000);
                        parkeingCount.release();

                    } else {
                        System.out.println("无可用停车位，寻找其他停车场");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
