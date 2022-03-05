package demo03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 封装线程同步方法
 */
public class Synchronized02 {
    public static void main(String[] args) throws InterruptedException {

        CounterPg counterPg1 = new CounterPg();
        Thread thread1 = new Thread(() -> {
            counterPg1.add(50);
        });
        thread1.start();
        thread1.join();
        Thread thread2 = new Thread(() -> {
            counterPg1.dec(20);
        });
        thread2.start();
        thread2.join();
        System.out.println(counterPg1.getCount());
        System.out.println(counterPg1.getPair().count);

        CounterPg counterPg2 = new CounterPg();
        Thread thread3 = new Thread(() -> {
            counterPg2.add(100);
        });
        thread3.start();
        thread3.join();
        Thread thread4 = new Thread(() -> {
            counterPg2.dec(33);
        });
        thread4.start();
        thread4.join();
        System.out.println(counterPg2.getCount());
        System.out.println(counterPg2.getPair().count);

    }
}

/**
 * 把线程同步的基础类型的值单独封装
 */
class CounterPg {
    private int count = 0;

    /*public void add(int num) {
        synchronized(this) {
            this.count += num;
        }
    }*/

    // 这种写法等价于上面的写法
    public synchronized void add(int num) {
        count += num;
    }

    public synchronized void dec(int num) {
        count -= num;
    }

    public int getCount() {
        return count;
    }

    // 如返回值是对象，也要加锁才线程安全
    public synchronized Pair getPair() {
        Pair pair = new Pair();
        pair.count = count;
        return pair;
    }
}

class Pair {
    public int count = 0;
}