package com.bruce.demo;

public class MemoryVisibleDemo {

    public static void main(String[] args) throws InterruptedException {
        final Example example = new Example();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                example.execute();
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread set stop to true");
        example.setStop(true);
        long start = System.currentTimeMillis();
        thread.join();
        System.out.println("example thread execute complete with :" + (System.currentTimeMillis() - start));
    }

   static class Example {
        private volatile boolean stop = false;
        private int i;
        public void execute() {
            while (!stop) {
               i++;
            }

            System.out.println("Thread stopped");
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }
}
