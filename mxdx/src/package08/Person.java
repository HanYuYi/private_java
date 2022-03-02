package package08;

public class Person {

    public final String name = "张三";

    final String names = "李四";

    public void say() {
        System.out.println("Hello, Package！");
    }

    void hello() {
        System.out.println("Hello, Package！");
    }
}

class Students {
    final void hi() {
        System.out.println("Hello, Package！");
    }
}