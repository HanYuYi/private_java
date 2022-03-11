import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Scanner04 {
    public static void main(String[] args) {

        /*Scanner scanner = new Scanner(System.in);

        double d = 0.0d;
        System.out.println("请输入数字：");
        try {
            while (scanner.hasNextDouble()) {
                d += scanner.nextDouble();
            }
        } catch (Exception e) {
            System.out.println("您输入的不是数字");
            d = 0.0d;
        }

        if (scanner.hasNextLine()) {
            if (scanner.nextLine().toString().equals("q")) {
                System.out.println("和为：" + d);
                scanner.close();
            }
        }*/

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }
}
