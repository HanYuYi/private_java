import java.util.ArrayList;
import java.util.Objects;

public class Equals01 {
    public static void main(String[] args) {
        ArrayList<Person> p01 = new ArrayList<>();
        p01.add(new Person("张三", 19));
        p01.add(new Person("李四", 30));
        p01.add(new Person("王武", 27));

        System.out.println(p01.contains(new Person("张三", 19)));
    }
}

/**
 * List类，如果是自定义对象，自己对象内部也要覆些equals方法
 * 覆写equals，因为Integer、String内部已经覆写了equals方法
 */
class Person {
    public String name;
    public int age;

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 引用类型用equals比较，基本类型用==比较
     * @param o
     * @return boolean
     */
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
