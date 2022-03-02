package demo02;

public class Main {
    public static void main(String[] args) {
        Person<String, Integer> p = new Person<>("大卫", 24);

        String name = p.getName();
        int age = p.getAge();
        String sex = p.backSex("男");
        String adds = Person.show("河南焦作市");

        System.out.println(name + "：" + age + "，性别：" + sex + "，来自" + adds);


    }
}
