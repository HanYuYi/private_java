package constructor01;

/**
 * 第一章 构造器
 * @author yuyihan
 *
 */

public class Main {
    public static void main(String[] args) {
        Person s1 = new Person();
        System.out.println(s1.getName() + ' ' + s1.getAge());

        Person s2 = new Person("王五");
        System.out.println(s2.getName() + ' ' + s2.getAge());

        Person s3 = new Person("马六", 26);
        System.out.println(s3.getName() + ' ' + s3.getAge());
    }
}

class Person {
    private String name;
    private int age;

    public Person() {}

    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


}

