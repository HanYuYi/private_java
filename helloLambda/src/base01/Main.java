package base01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public interface Printer {
        void print(String val);
    }

    public void printSomething(String something, Printer printer) {
        printer.print(something);
    }

    public static void main(String[] args) {
        Printer printer = new Printer() {
            @Override
            public void print(String val) {
                System.out.println(val);
            }
        };

        Main main = new Main();

        // 匿名内部类
        main.printSomething("小明来了...", printer);
        // lambda表达式
        main.printSomething("小花来了...", val -> System.out.println(val));

        // stream操作array
        String[] arr = new String[] { "w", "a", "v", "x", "c", "l" };
        List<String> collect = Arrays.stream(arr)
                .filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);

        // stream操作set
        Set<String> set = Set.of("Chinese", "Japanese", "English");
        set.stream()
                // 这种协防叫方法引用，使用时直接去掉方法的（）
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        for (String s : set) {
            System.out.println(s);
        }

        // 用map()把一组String转换为LocalDate
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Stream<String> stringStream = Stream.of("2022-07-31 09:00:00", "2022-03-14 09:00:00", "2022-09-01 09:00:00");
        stringStream
                .map(date -> LocalDateTime.parse(date, dateTimeFormatter))
                .map(LocalDateTime::toLocalDate)
                .forEach(System.out::println);
    }
}
