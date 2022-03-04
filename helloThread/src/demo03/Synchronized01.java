package demo03;

import java.util.Arrays;

/**
 * synchronized 线程变量同步
 * 两个及以上的线程操作同一个变量的时候，用于保证是原子操作（数据同步、同一时间只能由一个线程操作）的技术
 */
public class Synchronized01 {
    public static void main(String[] args) throws InterruptedException {
        AddStudentCount addStudentCount = new AddStudentCount();
        DecStudentCount decStudentCount = new DecStudentCount();
        addStudentCount.start();
        decStudentCount.start();

        addStudentCount.setVal(10, 33);
        addStudentCount.setVal(10, 33);

        addStudentCount.join();
        decStudentCount.join();
        System.out.println(Counter.studentCount);
        System.out.println(Counter.teacherCount);
        System.out.println("-------------------------------------");
        System.out.println(addStudentCount.getAge());
        System.out.println(addStudentCount.getScore());
        System.out.println(Arrays.toString(addStudentCount.getAll()));

        System.out.println("end");
    }
}

class Counter {
    public static final Object studentLock = new Object();
    public static final Object teacherLock = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class AddStudentCount extends Thread {
    private int age = 0;
    private int score = 0;

    private int[] all;

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public int[] getAll() {
        return all;
    }

    public void setVal(int age, int score) {
        // 如果是多行赋值，也必须保证是同步操作：如
        /*synchronized(this) {
            this.age = age;
            this.score = score;
        }*/
        // 可以通过转换，可以把非原子操作变为原子操作，就不用再同步
        int[] ps = new int[] { age, score };
        this.all = ps;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.studentLock) {
                Counter.studentCount += 1;
            }
        }
    }
}

class DecStudentCount extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.studentLock) {
                Counter.studentCount -= 1;
            }
        }
    }
}

class AddTeacherCount extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.teacherLock) {
                Counter.teacherCount += 1;
            }
        }
    }
}

class DecTeacherCount extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.teacherLock) {
                Counter.teacherCount += 1;
            }
        }
    }
}
