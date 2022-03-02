
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过Class实例获取class信息的方法称为反射: Reflection
 */
public class Demo01 {
    /**
     * 获取class 的 Class实例，有三个方法
     */

    // Class cls = String.class;

    // String s = "Hello";
    // Class cls = s.getClass();

    // Class cls = Class.forName("java.lang.String");

    /**
     * Field[] getFields()：获取所有包括父类public的field
     * Field[] getDeclaredFields()：获取当前类的所有field
     * Field getField(name)：根据字段名获取当前类或父类的某个public的field
     * Field getDeclaredField(name)：根据字段名获取当前类的某个field
     */

    public static void main(String[] args) throws Exception {
        // 获取field 的值: get()
        Person p1 = new Person();
        Class cls1 = Person.class; // Class.forName("完整类名")  new Person().getClass()  Integer.TYPE
        Field fie1 = cls1.getDeclaredField("age");
        // 暴力反射
        fie1.setAccessible(true);
        Object val1 = fie1.get(p1);
        System.out.println(val1);

        // 设置field 的值: set()
        fie1.set(p1, 13);
        System.out.println(fie1.get(p1));

        // 获取method
        Method m1 = cls1.getDeclaredMethod("backName", String.class);
        System.out.println(m1);
        // 调用method: invoke
        String in1 = (String) m1.invoke(p1, "bill");
        System.out.println(in1);

        // 调用非public static method: invoke(null)
        Method sm1 = cls1.getDeclaredMethod("allprint");
        sm1.setAccessible(true);
        sm1.invoke(null);

        // 创建实例: 先get，再newInstance()
        Constructor cons1 = cls1.getDeclaredConstructor(int.class);
        Person co1 = (Person) cons1.newInstance(28);
        System.out.println(co1);

        // 获取父类Class: getSuperclass()
        Class sc1 = cls1.getSuperclass();
        System.out.println(sc1);
        Class sc2 = sc1.getSuperclass();
        System.out.println(sc2);

        // 获取实现的接口
        Class[] int1 = cls1.getInterfaces();
        for(Class i: int1) {
            System.out.println(i);
        }

        // 判断两个Class实例，一个向上转型是否成立: isAssignableFrom()
        boolean isA1 = IPerson.class.isAssignableFrom(cls1);
        System.out.println(isA1);
        boolean isA2 = cls1.isAssignableFrom(Object.class);
        System.out.println(isA2);
    }

}

interface IPerson {
    public int backAge();
}

class Person implements IPerson {
    String names;
    private int age;

    public Person() {}

    Person(int age) {
        this.age = age;
    }

    static void allprint() {
        System.out.println("hello, everyone!");
    }

    String backName(String name) {
        return "hello, " + name;
    }

    public int backAge() {
        return age;
    }
}
