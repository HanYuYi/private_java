import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class DemoByReflection {

    private static Map<Integer, String> modifierMap = new HashMap<>();

    static {
        modifierMap.put(0, "default");
        modifierMap.put(1, "public");
        modifierMap.put(2, "private");
        modifierMap.put(4, "protected");
        modifierMap.put(8, "static");
        modifierMap.put(16, "final");
    }

    public static void main(String[] args) {
        InfoReflection info = new InfoReflection();
        Class<? extends InfoReflection> infoReflection = info.getClass();

        // Field
        Field[] fieldS = infoReflection.getDeclaredFields();
        Field currentFiled = null;
        for (Field field : fieldS) {
            currentFiled = field;

            try {
                if (Modifier.isPrivate(currentFiled.getModifiers())) {
                    currentFiled.setAccessible(true);
                }
                if ("age".equals(currentFiled.getName())) {
                    try {
                        currentFiled.set(info, 25);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(currentFiled.get(info));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(currentFiled.getType());
            // int 修饰符
            System.out.println(modifierMap.get(currentFiled.getModifiers()));
            System.out.println(currentFiled.getName());
            System.out.println("------------------------");

        }

        // method
        try {
            Method method = infoReflection.getDeclaredMethod("postHandler", String.class);
            System.out.println(method.getReturnType());
            System.out.println(modifierMap.get(method.getModifiers()));
            System.out.println(method.getDeclaringClass());
            // 获取方法参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType);
            }
            // 获取方法注解的值
            System.out.print(method.getAnnotation(Report.class).value());
            System.out.print("\t" + method.getAnnotation(Report.class).scope() + "\n");

            // 获取方法参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                // 获取方法参数注解的值
                System.out.println(parameter.getAnnotation(PathPer.class).value());
            }

            // 执行方法
            method.invoke(info, "lisi456");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Report {
    String value() default "";
    String scope() default "method";
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface PathPer {
    String value() default "";
}

class InfoReflection {

    public String name = "Hope";

    private int age = 20;

    int gender = 0;

    @Report(value = "/login", scope = "mm")
    public void postHandler(@PathPer("zhangsan123") String username) {
        System.out.println("Hello " + username +", login succeeded!......");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }
}

