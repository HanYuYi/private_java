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

class Realization implements Runnable {

    public int ticket = 30;

    public volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            synchronized(this) {
                if (ticket < 0) {
                    running = false;
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": 还剩" + ticket + "张票");
                ticket--;
            }
        }
    }
}
