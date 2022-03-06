package demo04;

/**
 * wait notify wait(long) notifyAll
 * 线程的等待唤醒机制，两个或多个线程协同工作，可以看成是 async 和 await
 * 用读取数据的例子来说明
 * 注意：和同步机制不同的是，在等待的时候，锁会被释放进入池子中，在被唤醒时，又重新竞争CPU执行权
 */
public class TestWaitAndnotify {
    private static String data = "";

    private static Object lock = new Object();

    public static void main(String[] args) {

        // 请求数据的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：开始请求数据，请等待...");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "：拿到数据了，数据为：" + data);
            }
        }, "请求数据的线程").start();

        // 读取数据的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模拟读取数据用了两秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    data = "1";
                    System.out.println(Thread.currentThread().getName() + "：数据读取成功！");
                    lock.notify();
                }
            }
        }, "读取数据的线程").start();
    }
}
