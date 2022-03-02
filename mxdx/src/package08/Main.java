package package08;

/**
 * 包 package
 * @author yuyihan
 *
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Person().name);
        System.out.println(new Person().names);

        new Person().hello();
        new Person().say();

        new Students().hi();
    }
}