package demo03;

/**
 * 三个窗口（线程）同时卖30张票的案例
 */
public class SellTicket {

    public static void main(String[] args) {

        Realization realization = new Realization();

        Thread t1 = new Thread(realization, "窗口1");
        Thread t2 = new Thread(realization, "窗口2");
        Thread t3 = new Thread(realization, "窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Locker {

    public static final Object lockObj = new Object();

    public static int ticket = 30;

}

class Realization implements Runnable {
    public static volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            if (Locker.ticket <= 1) {
                running = false;
                break;
            }
            synchronized(Locker.lockObj) {
                System.out.println(Thread.currentThread().getName() + ": 还剩" + Locker.ticket + "张票");
                Locker.ticket--;
            }
        }
    }
}
