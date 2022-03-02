package demo04;

public class Teacher extends Person {
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                '}';
    }
}
