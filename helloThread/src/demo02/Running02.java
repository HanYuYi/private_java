package demo02;

/**
 * 通过volatile 共享线程变量isRunning，并将isRunning及时更新到内存中来中断线程
 */
public class Running02 {
    public static void main(String[] args) throws InterruptedException {
        HelloThread helloThread = new HelloThread();
        helloThread.start();
        Thread.sleep(8);
        helloThread.isRunning = false;
    }
}

class HelloThread extends Thread {
    public volatile boolean isRunning = true;

    @Override
    public void run() {
        int n = 0;
        while (isRunning) {
            System.out.println("hello " + n);
        }
        System.out.println("hello end");
    }
}
