package demo06;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 因为集合对象都是线程不安全的，本张介绍几个线程安全的集合对象
 * CopyOnWriteArrayList
 * CopyOnWriteArraySet
 * ConcurrentHashMap
 */
public class Main {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        arrayList.add("" + Thread.currentThread().getName() + finalI + "-" + j);
                    }
                }
            });
        }

        pool.shutdown();
        while (!pool.isTerminated()) {}
        System.out.println(arrayList.size());
        for (String s : arrayList) {
            System.out.println(s);
        }
    }
}
