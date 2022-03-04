package demo03;

/**
 * 封装线程同步方法
 */
public class Synchronized02 {
    public static void main(String[] args) {
        AddCount addCount = new AddCount();
    }
}

/**
 * 把线程同步单独封装
 */
class CounterPg {
    private int count = 0;

    /*public void add(int num) {
        synchronized(this) {
            this.count += num;
        }
    }*/

    // 这种写法等价于上面的写法
    public synchronized void add(int num) {
        count += num;
    }

    public synchronized void dec(int num) {
        count -= num;
    }

    public int getCount() {
        return count;
    }
}

class AddCount extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 10000; i++) {

        }
    }
}


