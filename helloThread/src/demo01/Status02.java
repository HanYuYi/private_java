package demo01;

/**
 * 线程的状态
 * New：新创建的线程，未执行
 * Runnable：运行中的线程，正在执行run()方法的Java代码
 * Blocked：运行中的线程，因某些操作被阻塞而挂起
 * Waiting：运行中的线程，因某些操作在等待中
 * Timed Waiting：运行中的线程，因执行sleep()方法正在计时等待
 * Terminated：线程已终止，因run()方法执行完毕
 * interrupted：中断线程
 *
 * setDaemon 守护线程（不能持有任何需要关闭的资源）
 * sleep 强迫线程暂停
 * setPriority 设定优先级
 * join 等待其执行结束
 */
public class Status02 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("main run");

        Thread thread1 = new Thread() {
            public void run() {
                System.out.println("thread run");
                System.out.println("thread end");
                // 检测自身是否是interrupted状态，响应中断请求
                while (!isInterrupted()) {
                    System.out.println("hello!");
                }
                System.out.println("thread run");
            }
        };

        // 开启守护线程
        thread1.setDaemon(true);

        thread1.start();

        // 设定优先级
        // thread1.setPriority(Thread.MAX_PRIORITY);

        // 强迫线程暂停
         try {
             Thread.sleep(1);
         } catch (InterruptedException e) {}

         // 发出中断线程请求
          thread1.interrupt();

        // 等待thread1执行结束再向下执行，并且也能指定时间
        thread1.join();

        System.out.println("main end");
    }
}
