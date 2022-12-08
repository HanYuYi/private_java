public class DemoByThread {
    private static Long startTime = 0L;

    private static Long endTime = 0L;

    public static void main(String[] args) {

        Counter counter = new Counter();
        counter.setTime(10000);

        startTime = System.currentTimeMillis();
        // 多线程调用
        Thread add = new Thread(() -> {
            counter.add();
        });

        Thread dec = new Thread(() -> {
            counter.dec();
        });

        add.start();
        dec.start();


        // 单线程调用
        // counter.add();
        // counter.dec();
        System.out.println("dec----: " + counter.getCount());
        endTime = System.currentTimeMillis();
        System.out.println("time: " + (endTime - startTime));
    }
}


class Counter {

    private int status = 0;

    private int count = 0;

    private int time = 100;

    private int changeVal = 1;

    public void setTime(int time) {
        this.time = time;
    }

    public void setChangeVal(int changeVal) {
        this.changeVal = changeVal;
    }

    public synchronized void add() {
        for (int i = 0; i < time; i++) {
            count += changeVal;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
        status++;
        if (status >= 2) {
            this.notifyAll();
        }
    }

    public synchronized void dec() {
        for (int i = 0; i < time - 1; i++) {
            count -= changeVal;
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
        status++;
        if (status >= 2) {
            this.notifyAll();
        }
    }

    public synchronized int getCount() {
        while (status < 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}