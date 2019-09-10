package com.bruce.demo;

public class DemoThread extends Thread {

    public static void main(String[] args) {
        Thread thread = new DemoThread();
        thread.start();
    }

    public void run() {
        //business logic
        System.out.println("demo thread is ruuing");
    }
}



