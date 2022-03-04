package demo03;

public class SellTicket {
    public static int ticket = 30;

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

class Counters {
    public synchronized void dec() {
        SellTicket.ticket--;
    }
}

class Realization implements Runnable {
    public static volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            new Counters().dec();
            System.out.println(Thread.currentThread().getName() + ": " + SellTicket.ticket);
            if (SellTicket.ticket <= 0) {
                Realization.running = false;
            }
        }
    }
}
