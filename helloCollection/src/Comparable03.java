import java.util.Comparator;
import java.util.TreeMap;

public class Comparable03 {
    public static void main(String[] args) {

        TreeMap<Person02, Integer> treeMap = new TreeMap<>(new Comparator<Person02>() {
            @Override
            public int compare(Person02 o1, Person02 o2) {
                // 根据key的age字段排序
                if (o1.age == o2.age) {
                    return 0;
                }
                return o1.age < o2.age ? 1 : -1;
            }
        });

        treeMap.put(new Person02("宋江", 54), 1);
        treeMap.put(new Person02("卢俊义", 53), 1);
        treeMap.put(new Person02("杨志", 48), 3);
        treeMap.put(new Person02("林冲", 42), 2);

        for (Person02 t : treeMap.keySet()) {
            System.out.println(t);
        }
    }
}

/**
 * 使用TreeMap时，如果key是自定义对象，要实现Comparable接口，如果没有，必须在创建TreeMap时指定自定义排序算法： new Comparator<K>() {}
 * 使用TreeSet时同上
 *
 * 自定义排序算法：a和b，如果a<b，(a<b ? -1 : 1)--正序  (a<b ? 1 : -1)--倒序
 */
class Person02 {
    public String name;
    public int age;

    public Person02(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person02{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}