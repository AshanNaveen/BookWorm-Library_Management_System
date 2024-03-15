package lk.ijse.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static Timestamp getDueTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());

        calendar.add(Calendar.WEEK_OF_YEAR, 2);

        Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());
        return newTimestamp;
    }
    public static String dateNow() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(new Date());
    }

    public static String convertDate(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        Date date = new Date(timestamp.getTime());
        return sdf.format(date);
    }

    public static String yearNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }

    public static int getDays(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    public static String monthNow() {
        LocalDate localDate = LocalDate.now();
        return String.valueOf(localDate.getMonth());
    }

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm aa");
               return dateFormat.format(new Date()) ;
    }
    public static String timeNowForName() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH_mm");
               return dateFormat.format(new Date()) ;
    }
}
