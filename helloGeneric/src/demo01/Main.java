package demo01;

import java.util.Arrays;

/**
 * 泛型 generics
 *
 */
public class Main {
    public static void main(String[] args) {
        Person[] pList = new Person[] {
                new Person("edison", 22),
                new Person("amy", 23),
                new Person("tara", 24)
        };
        Arrays.sort(pList);
        System.out.println(Arrays.toString(pList));
    }
}

/**
 * Arrays.sort(Object[])可以对任意数组排序，但待排序的元素必须实现Comparable<T>泛型接口
 */
class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return this.name + "-" + this.age;
    }
}
