package demo04;

import java.util.*;

/**
 * extends: 限制只能传当前类或子类，可以用在类或方法上
 * super: 限制只能传当前类或父类，只能用在方法上
 */
public class Main {
    public static void main(String[] args) {
        Info<Student> p1 = new Info<>();
        Info<Teacher> p2 = new Info<>();
        // Info<Date> p3 = new Info<>(); // X

        Person<String> p = new Person<>();
        p.setName("hello");
        System.out.println(p.getName());

        Info1 p4 = new Info1();
        p4.show(new Person());
        p4.show(new Student());
        p4.show(new Teacher());

        p4.biling(new Student());
        p4.biling(new Person());

        // p4.show(new String()); // X
        p4.biling(new Teacher());

    }
}
