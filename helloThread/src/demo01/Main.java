package demo01;

/**
 * 多线程
 * 新线程创建有两种方式
 */
public class Main {
    public static void main(String[] args) {

        // 第一种，第一种实例化方式
        MyThread myThread = new MyThread();
        myThread.start();

        // 第一种，第二种实例化方式，用Java8引入的lambda语法简写
        Thread thread2 = new Thread(() -> {
            System.out.println("start new Thread 3");
        });
        thread2.start();

        // 第一种，第三种实例化方式，这种和上种差不多
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                System.out.println("start new Thread 4");
            }
        };
        thread3.start();

        // 第二种
        Thread t1 = new Thread(new MyRunnable(), "Runnable01");
        Thread t2 = new Thread(new MyRunnable(), "Runnable02");
        t1.start();
        t2.start();

    }
}

/**
 * 第一种 extends Thread
 * 这种有三张实例化方式
 */
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("start new thread 1");
    }
}

/**
 * 第二种 implements Runnable，并将实例传入构造方法
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": start new thread 2");
    }
}
