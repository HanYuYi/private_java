package demo01;

/**
 * 多线程
 * 新线程创建有四种方式
 */
public class Main {
    public static void main(String[] args) {

        // 第一种
        MyThread myThread = new MyThread();
        myThread.start();

        // 第二种
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();

        // 第三种，用Java8引入的lambda语法简写
        Thread thread2 = new Thread(() -> {
            System.out.println("start new Thread 3");
        });
        thread2.start();


        // 第四种
        Thread thread3 = new Thread() {
            public void run() {
                System.out.println("start new Thread 4");
            }
        };
        thread3.start();
    }
}

/**
 * 第一种 extends Thread
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
        System.out.println("start new thread 2");
    }
}
