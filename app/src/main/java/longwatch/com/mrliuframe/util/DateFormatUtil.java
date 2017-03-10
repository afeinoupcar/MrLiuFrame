package longwatch.com.mrliuframe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by justin on 16/12/4.
 */
public class DateFormatUtil {
    private static final SimpleDateFormat sFormat = new SimpleDateFormat();
    public static final String FORMAT_START_DATE = "yyyy-MM-dd 00:00:00";
    public static final String FORMAT_END_DATE = "yyyy-MM-dd HH:mm:ss";

    public static Date getDateStart(String time) {
        return getDate(time, FORMAT_START_DATE);
    }

    public static Date getDateEnd(String time) {
        return getDate(time, FORMAT_END_DATE);
    }

    public static Date getDate(String time, String pattern) {
        try {
            sFormat.applyPattern(FORMAT_END_DATE);
            return sFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDateStart(Date date) {
        return formatDate(date, FORMAT_START_DATE);
    }

    public static String formatDateEnd(Date date) {
        return formatDate(date, FORMAT_END_DATE);
    }

    public static String formatDate(Date date, String format) {
        sFormat.applyPattern(format);
        return sFormat.format(date);
    }
}
