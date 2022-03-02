
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 注解: Annotation，着个demo用到了前面所学的反射
 */
public class Demo01 {
    public static void main(String[] args) {

        Class cls1 = Person.class;
        Report r1 = (Report) cls1.getAnnotation(Report.class);

        // 读取普通注解
        int age = r1.age();
        String value = r1.value();
        String name = r1.name();
        System.out.println(name);
        System.out.println(age);
        System.out.println(value);

        // 读取方法参数注解，因为方法参数是一个数组，而每个参数又可以定义多个注解，所以，一次获取方法参数的所有注解就要用二维数组来表示
        // public void hello(@NotNull @Range(max=5) String name, @NotNull String prefix) {}

        try {
            check(new Person());
        }
        catch (ReflectiveOperationException e) {
            System.out.println(e);
        }

        try {
            checkMethodParam();
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    // 用反射检测字段是否在注解规定的范围内
    public static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException {
        for (Field filed: person.getClass().getFields()) {
            // filed.setAccessible(true);
            Annotation[] r0 = filed.getDeclaredAnnotations();
            for (Annotation r: r0) {
                System.out.println(r);
            }


            Range range = filed.getAnnotation(Range.class);
            if (range != null) {
                Object value = filed.get(person);
                if (value instanceof Number) {
                    int val = (int) value;
                    if (val < range.min() || val > range.max()) {
                        throw new IllegalArgumentException("Range field: " + filed.getName());
                    } else {
                        System.out.println(val);
                    }
                }
            }
        }
    }

    public static void checkMethodParam() throws ClassNotFoundException {
        String classTitle = "annotat.Person";
        Class p = Class.forName(classTitle);
        try {
            Method me = p.getDeclaredMethod("setAge", int.class);
            /*Annotation[][] params = me.getParameterAnnotations();
            for (Annotation[] list: params) {
                for (Annotation curPar: list) {
                }
            }*/
            MePar paramsAn = me.getAnnotation(MePar.class);
            Parameter[] paramsVal = me.getParameters();
            String type = String.valueOf(paramsVal[0].getType());
            if (paramsAn != null) {
                if (type.equals(paramsAn.value())) {
                    System.out.println("类型校验成功！");
                } else {
                    throw new NumberFormatException("类型错误");
                }
            }

        } catch (NoSuchMethodException e) {
            e.getStackTrace();
        }
    }
}

/**
 * 元注解
 * @Target 应用于哪些位置: {  必须
 *     类或接口：ElementType.TYPE；
 *     字段：ElementType.FIELD
 *     方法：ElementType.METHOD
 *     构造方法：ElementType.CONSTRUCTOR
 *     方法参数：ElementType.PARAMETER
 * }
 * @Retention 定义注解的生命周期: {  必须
 *     仅编译期：RetentionPolicy.SOURCE
 *     仅class文件：RetentionPolicy.CLASS
 *     运行期：RetentionPolicy.RUNTIME ---- 用的最多
 * }
 * @Repeatable 注解是否可以重复
 * @Inherited 子类是否可继承父类定义，仅对class的@Target(ElementType.TYPE)有效
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Report {
    String value() default "";
    String name() default "everyone";
    int age() default 18;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Range {
    int min() default 1;
    int max() default 100;
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface MePar {
    int value() default 0;
}

@Report
class Person {
    @Range
    public int age = 20;

    void setAge(@MePar int age) {
        this.age = age;
    }
}
