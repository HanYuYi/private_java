package preventExtends03;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person();
        System.out.println(p1.sayHello());

        Person p2 = new Person("王元禄");
        System.out.println(p2.sayHello());

        Person p3 = new Person(28);
        System.out.println(p3.sayHello());

        Person p4 = new Person("元芳", 33);
        System.out.println(p4.sayHello());

        Student p5 = new Student("元芳", 33, 78);
        System.out.println(p5.sayHello());

        // 向上转型
        System.out.println(new Student("元芳", 33, 78) instanceof Person);

        // 向下转型
        System.out.println(new Person() instanceof Student);
    }
}

/**
 * 阻止继承
 * sealed permits
 * @author yuyihan
 * 正常情况下，只要某个class没有final修饰，任何类都可以从该class继承，
 * 但Java 15开始，新增sealed修饰class，并通过permits指定能从该class继承的子类名称
 */
sealed class Person permits Student {
    protected String name;
    protected int age;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String sayHello() {
        String info = null;

        if (name != null || age > 0) {
            if (name != null && name != "") {
                info = "你好！" + name;
            }
            if (age > 0) {
                if (info != null) {
                    info += "，您的年龄为:" + age;
                } else {
                    info = "您的年龄为:" + age;
                }
            }
        } else {
            info = "你的信息未知";
        }

        return info;
    }
}

/**
 * @author yuyihan
 * 父类被sealed permits 修改的，子类必须用final修饰
 * final 修饰的类不能被继承，final 修饰的方法不能被覆写，final修饰的字段不能被修改，但在构造器中可以
 */
final class Student extends Person {
    private int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    @Override
    public String sayHello() {
        return super.sayHello() + "，你的分数为：" + score;
    }
}
