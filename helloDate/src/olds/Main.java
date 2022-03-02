package olds;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Date, Calendar, SimpleDateFormat
 */
public class Main {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR),
                month = instance.get(Calendar.MONTH),
                day = instance.get(Calendar.DAY_OF_MONTH),
                hour = instance.get(Calendar.HOUR_OF_DAY),
                minute = instance.get(Calendar.MINUTE),
                second = instance.get(Calendar.SECOND);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(instance.getTime());

        System.out.println(format);
        for(String id : TimeZone.getAvailableIDs()) {
            System.out.println(id);
        }
        System.out.println(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
