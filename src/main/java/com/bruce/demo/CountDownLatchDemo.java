package com.bruce.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static final Map<String, Integer> sizeMap = new ConcurrentHashMap<String, Integer>();


    public static void main(String[] args) throws InterruptedException {
        String[] strList = {"abdedf","cdefjhfds","fds","ghklmhr","jllsdafd","monqlngfskh",};
        CountDownLatch countDownLatch = new CountDownLatch(strList.length);
        for (String str: strList) {
            new Thread(new SubTask(str,countDownLatch)).start();
        }

        countDownLatch.await();

        System.out.println(sort());

    }
    private static List<Map.Entry<String, Integer>> sort() {
        List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(sizeMap.entrySet());
        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o1.getValue(),o2.getValue());
            }
        });

        return sortList;
    }

    static class SubTask implements Runnable{
        private String str;
        private CountDownLatch countDownLatch;

        public SubTask(String str, CountDownLatch countDownLatch) {
            this.str = str;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            int length = str.getBytes().length;
            sizeMap.put(str,length);
            countDownLatch.countDown();
        }
    }


}
