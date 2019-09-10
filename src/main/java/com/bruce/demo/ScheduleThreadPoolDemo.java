package com.bruce.demo;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {

        //scheduleAtFixRate();
       //scheduleWithDelay();
       schedule();
    }

    /**
     * 以固定的时间间隔运行任务，如果任务的执行完成时间
     */
    private static void scheduleAtFixRate() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("schedule at fix rate running at : " + new Date());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,1000,TimeUnit.MILLISECONDS);
    }

    private static void scheduleWithDelay() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("schedule with fix delay running at : " + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,1000, TimeUnit.MILLISECONDS);
    }

    private static void schedule() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                System.out.println("schedule with fix delay running at : " + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000, TimeUnit.MILLISECONDS);
    }
}
