package demo05;

import java.util.concurrent.*;

/**
 * Executors 线程池（线程必须用实现实现接口的方式创建），
 * Callable 第三种创建方式，创建一个有返回值的线程
 * 用多个线程同时读取多个数据的例子来掩饰
 */

/**
 * Callable 创建有返回值的线程
 */
class MyCallable implements Callable<Integer> {
    private String name = "";

    public MyCallable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 返回Feture
    @Override
    public Integer call() {
        int val = (int) (Math.random() * 100);
        System.out.println(this.name + "返回了结果：" + val);
        return val;
    }
}

public class Callable01 {
    public static void main(String[] args) {
        /**
         * 创建线程池，有四种选择
         * newFixedThreadPool
         * newCachedThreadPool
         * newSingleThreadExecutor
         * newScheduledThreadPool
         * 创建线程是很耗费资源的，因此线程池油然而生
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建线程
        MyCallable callableThread1 = new MyCallable("线程1");
        MyCallable callableThread2 = new MyCallable("线程2");
        MyCallable callableThread3 = new MyCallable("线程3");
        // 提交到线程池中
        Future<Integer> submit1 = executorService.submit(callableThread1);
        Future<Integer> submit2 = executorService.submit(callableThread2);
        Future<Integer> submit3 = executorService.submit(callableThread3);

        try {
            System.out.println(submit1.get() + submit2.get() + submit3.get());
            // 关闭线程池，通常不关闭
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
