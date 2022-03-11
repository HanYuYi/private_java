import java.util.*;

public class SetDemo04 {
    public static void main(String[] args) {
        List<Message04> received = List.of(
                new Message04(1, "Hello!"),
                new Message04(2, "发工资了吗？"),
                new Message04(2, "发工资了吗？"),
                new Message04(3, "去哪吃饭？"),
                new Message04(3, "去哪吃饭？"),
                new Message04(4, "Bye")
        );
        List<Message04> displayMessages = process(received);
        for (Message04 message : displayMessages) {
            System.out.println(message.text);
        }
    }

    static List<Message04> process(List<Message04> received) {
        // 按sequence去除重复消息

        // 第二种方式：创建TreeSet时必须传入一个Comparator对象
        TreeSet<Message04> treeSet = new TreeSet<>(new Comparator<Message04>() {
            @Override
            public int compare(Message04 o1, Message04 o2) {
                if (o1.sequence == o2.sequence) return 0;
                return o1.sequence < o2.sequence ? -1 : 1;
            }
        });
        for (Message04 r : received) {
            treeSet.add(r);
        }

        List<Message04> back = new ArrayList<>();
        for (Message04 t : treeSet) {
            back.add(t);
        }
        return back;
    }
}

/**
 * 自定义对象必须实现Comparable接口，如果没有，创建TreeSet时必须传入一个Comparator对象，来保证唯一性
 */


class Message04 {
    public final int sequence;
    public final String text;
    public Message04(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message04{" +
                "sequence='" + sequence+ '\'' +
                ", text=" + text+
                '}';
    }
}

/**
 * 第一种方式：实现Comparable接口
 */
class Message04C implements Comparable<Message04C> {
    public final int sequence;
    public final String text;
    public Message04C(int sequence, String text) {
        this.sequence = sequence;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message04{" +
                "sequence='" + sequence+ '\'' +
                ", text=" + text+
                '}';
    }

    @Override
    public int compareTo(Message04C o) {
        return this.sequence - o.sequence;
    }
}
