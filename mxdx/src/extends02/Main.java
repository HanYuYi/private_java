package extends02;

/**
 * 继承
 * @author yuyihan
 *
 */
public class Main {
    public static void main(String[] args) {
        Person s1 = new Person();
        System.out.println(s1.getAllAttr());

        Person s2 = new Person("张环");
        System.out.println(s2.getAllAttr());

        Person s3 = new Person(25);
        System.out.println(s3.getAllAttr());

        Person s4 = new Person("李朗", 30);
        System.out.println(s4.getAllAttr());

        System.out.println("------------------");

        Person d1 = new Student("元芳", 33, 95);
        System.out.println(d1.getAllAttr());

    }
}

class Person {
    protected String name;
    protected int age;
    // 定义多个构造方法，跟方法的重载很像
    public Person(){}

    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public String getAllAttr() {
        return ' ' + name + ' ' + age;
    }
}

class Student extends Person {
    private int score;

    // 子类不会继承父类的构造方法
    public Student(String name, int age, int score) {
        // 必选显示调用父类构造方法
        super(name, age);
        this.score = score;
    }

    @Override
    public String getAllAttr() {
        return "02" + super.name + ' ' + age + ' ' + this.score;
    }
}