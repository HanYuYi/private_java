package news;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime(本地时间) ZonedDateTime(时区时间)
 * DateTimeFormatter(格式化)
 */
public class Main {
    public static void main(String[] args) {
        //=============================================LocalDateTime===========================================//
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDate localDate = nowTime.toLocalDate();
        LocalTime localTime = nowTime.toLocalTime();

        System.out.println(nowTime);
        System.out.println(localDate);
        System.out.println(localTime);

        // 设置时间 of
        LocalDateTime of = LocalDateTime.of(2022, 2, 28, 21, 46, 50);
        // T 日期和时间分隔符
        LocalDateTime parse = LocalDateTime.parse("2022-02-28T21:46:50");
        System.out.println(of);
        System.out.println(parse);

        // 格式化日期时间：DateTimeFormatter
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().format(dateTimeFormatter));

        LocalDateTime parse1 = LocalDateTime.parse("2022-03-01 09:00:00", dateTimeFormatter);
        System.out.println(parse1);

        // 计算 plusXxx minXxxx
        LocalDateTime operation = LocalDateTime
                .now()
                .plusDays(2)
                .minusHours(3);
        System.out.println(operation);

        // 单独设置日期 withXxx，还有with()方法更方便的计算
        LocalDateTime with = LocalDateTime
                .now().
                withHour(20).
                withDayOfMonth(2);
        System.out.println(with);

        // isBefore isAfter
        System.out.println(LocalDateTime.parse("2022-02-28T21:46:50").isBefore(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().isAfter(LocalDateTime.parse("2022-02-28T21:46:50")));

        // 通过时区获取时间戳
        long l = ZonedDateTime
                .of(2022, 3, 1, 13, 10, 20, 0, ZoneId.of("America/New_York"))
                .toEpochSecond();
        long l1 = ZonedDateTime
                .now()
                .toInstant()
                .toEpochMilli();
        System.out.println(l);
        System.out.println(l1);

        // Duration：间隔的时分秒   Period：间隔的年月日
        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration d = Duration.between(start, end);
        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2022, 1, 9));
        System.out.println(d);
        System.out.println(p);
        System.out.println(Duration.ofHours(10));




        //=============================================ZonedDateTime===========================================//
        // 时区不同，但时间都是同一时刻
        ZonedDateTime now = ZonedDateTime.of(2022, 3, 1, 9, 0, 0, 0, ZoneId.of("Asia/Shanghai"));
        ZonedDateTime nynow = now.withZoneSameInstant(ZoneId.of("America/New_York"));

        // 时区不同，但时间都是同一时刻
        // ZonedDateTime now = ZonedDateTime.now();
        // ZonedDateTime nynow = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(now.toInstant().toEpochMilli());
        System.out.println(nynow.toInstant().toEpochMilli());
        System.out.println(now);
        System.out.println(nynow);

        // 当前北京时间转换为纽约时间
        ZonedDateTime BeijingNow = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime newYorkNow = BeijingNow.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(BeijingNow);
        System.out.println(newYorkNow);

    }
}
