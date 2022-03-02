import java.util.*;

public class EqualsAndHashCode02 {
    public static void main(String[] args) {
        // 如果HashMap要频繁扩容，建议在声明的时候直接指定容量和装前因子：new HashMap<>(20, 0.75f);
        Map<String, Person01> hsp1 = new HashMap<>();

        Person01 po1 = new Person01("聂震镖", 37);
        Person01 po2 = new Person01("耿少南", 26);

        hsp1.put("1", po1);
        hsp1.put("2", po2);

        // 如果不覆写，HashMap内部不能正确比较，这里就会为false
        System.out.println(hsp1.containsValue(new Person01("聂震镖", 37)));

        Iterator<String> iterator = hsp1.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

/**
 * 自定义类座位HashMap 的 key，需要覆写HashMap 的 equals 和 hashCode
 */
class Person01 {
    private String name;
    private int age;

    public Person01(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * ide 编写的覆写equals 和 hashCode
     * @return
     */
   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person01 person01 = (Person01) o;
        return age == person01.age && Objects.equals(name, person01.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }*/

    /**
     * 自己覆写equals 和 hashCode
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Person01) {
            Person01 p = (Person01) o;
            return Objects.equals(name, p.name) && age == p.age;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
