package demo03;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java5 新增的concurrent包下的 ReentrantLock 锁
 * 必须释放锁
 */
class MyList {
    private Lock lock = new ReentrantLock();

    private String[] strArr = {"Hi", "Java", "", ""};
    private int index = 2;

    public void add(String str) {
        lock.lock();
        try {
            strArr[index] = str;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
            System.out.println(Thread.currentThread().getName() + "：" + str);
        } finally {
            // 必须释放锁
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(strArr);
    }
}

public class ReentrantLock03 {
    public static void main(String[] args) {
        MyList myList = new MyList();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                myList.add("hello");
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                myList.add("world");
            }
        };

        Thread t1 = new Thread(r1, "线程一：");
        Thread t2 = new Thread(r2, "线程二：");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myList);
    }
}
