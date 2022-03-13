import java.util.*;

/**
 * 集合 Collection，List, Set, Map,     stack, Queue(单端), Deque(双端)
 * List, Set 有迭代器, Map使用keySet()
 */
public class Main {
    public static void main(String[] args) {

        /**
         * List : 如果元素是自定义对象， 要覆写equals方法
         *
         * ArrayList
         * LinkedList
         */
        // 两种创建方式
        List<String> arrayListOf = List.of("zz", "cc", "ww");
        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("-----------ArrayList-----------");
        arrayList.add("aaaa");
        arrayList.add("bbbb");
        arrayList.add("cccc");
        arrayList.add(1, "sss");
        // 推荐这两种遍历方式，效率高，实现了Iterable接口的，都可以用这两种方式遍历
        for (Iterator<String> it = arrayList.iterator(); it.hasNext();) {
            System.out.print(it.next() + ",");
        }
        System.out.println();
        for (String it : arrayList) {
            System.out.print(it + ",");
        }

        System.out.println();
        System.out.println("-----------LinkedList-----------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0, 9);
        System.out.println(linkedList.toString());


        /**
         * Set : 可以看成只存储key、不存储value的Map，如果key是自定义对象，一定要覆写equals和hashCode方法，Set继承了Map
         *
         * HashSet : 没有实现SortedSet接口，是无序的
         * TreeSet : 实现了SortedSet接口，是有序的，按红黑树排序
         * 自定义对象必须实现Comparable接口，如果没有，创建TreeSet时必须传入一个Comparator对象
         * linkedHashSet : 顺序是按照传入的自然顺序
         */
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        System.out.println("-----------HashSet-----------");
        System.out.println(hashSet.contains(1));


        /**
         * Map : 如果key是自定义对象，一定要覆写equals和hashCode方法
         *
         * HashMap
         * TreeMap : 实现了SortedSet接口，是有序的
         * 自定义对象座位key必须实现Comparable接口，如果没有，创建TreeSet时必须传入一个Comparator对象
         * EnumMap
         * LinkedHashMap
         */
        Map<String, Person> hashMap = new HashMap<>();
        Person hp1 = new Person("李四", 30);
        hashMap.put("01", hp1);
        hashMap.put("02", new Person("马六", 32));
        System.out.println("-----------HashMap-----------");
        Person targetHp1 = hashMap.get("01");
        System.out.println(targetHp1 == hp1);
        System.out.println(hashMap.containsKey("02"));
        for (String key : hashMap.keySet()) {
            System.out.print(key + ",");
        }
        System.out.println();
        for (Map.Entry<String, Person> kv : hashMap.entrySet()) {
            System.out.print(kv.toString() + ",");
        }

        System.out.println();
        System.out.println("-----------TreeMap-----------");
        Map<String, Person> treeMap = new TreeMap<>();
        Person tp1 = new Person("网九", 20);
        treeMap.put("01", tp1);
        System.out.println(treeMap.containsKey("01"));

    }
}

