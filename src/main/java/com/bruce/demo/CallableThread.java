package com.bruce.demo;

public class CallableThread {
    public static void main(String[] args) {

        Thread thread = new Thread(
                new Runnable() {
                    public void run() {
                        //business logic
                        System.out.println("thread is running");
                    }
                }
        );
        thread.start();
    }
}
