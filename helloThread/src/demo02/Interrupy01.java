package demo02;

/**
 * interrupt 中断线程
 */
public class Interrupy01 {
    public static void main(String[] args) throws InterruptedException {

        Thread person = new Person();
        person.start();
        Thread.sleep(1000);
        person.interrupt();
        person.join();
        System.out.println("all end");
    }
}


class Person extends Thread {
    @Override
    public void run() {
        Thread student = new Student();
        student.start();

        try {
            student.join();
        } catch (InterruptedException e) {
            System.out.println("student interrupted!");
        }

        student.interrupt();
    }
}


class Student extends Thread {
    @Override
    public void run() {
        int n = 0;
        while (! isInterrupted()) {
            n++;
            System.out.println("I'm Student " + n);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
