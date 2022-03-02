package demo04;

public class Info1 {

    public Info1() {}

    public void show(Person<? extends Person> par) {
        System.out.println(par);
    }

    public void biling(Person<? super Student> par) {
        System.out.println(par);
    }

}
