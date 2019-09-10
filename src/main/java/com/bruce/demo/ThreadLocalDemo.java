package com.bruce.demo;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread A get context value: " + Context.get());
                Context.set("thread A context value");
                System.out.println("thread A get context value after set: " + Context.get());
                try {
                    System.out.println("###thread A sleep for a while###");
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Context.clear();
                System.out.println("thread A get context value after clear: " + Context.get());
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread B get context value: " + Context.get());
                Context.set("thread B context value");
                System.out.println("thread B get context value after set: " + Context.get());
                Context.clear();
                System.out.println("thread B get context value after clear: " + Context.get());
            }
        });

        threadA.start();
        Thread.sleep(2000);
        threadB.start();
    }


    static class Context{

        private static ThreadLocal<String> value = new ThreadLocal<String>();

        public static String get() {
            return value.get();
        }

        public static void set(String v) {
           value.set(v);
        }

        public static void clear() {
            value.remove();
        }
    }
}
