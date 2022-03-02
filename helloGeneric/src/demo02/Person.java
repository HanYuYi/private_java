package demo02;

public class Person<T, E> {
    private T name;
    private E age;

    public Person(T name, E age) {
        this.name = name;
        this.age = age;
    }

    public T getName() {
        return name;
    }

    public E getAge() {
        return age;
    }

    public <R> R backSex(R sex) {
        return sex;
    }

    public static <W> W show(W arg) {
        return arg;
    }
}
