package demo04;

public class Person<T> {

    private T name;

    public void setName(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                '}';
    }
}
