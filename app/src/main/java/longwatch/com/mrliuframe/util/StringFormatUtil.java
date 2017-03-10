package longwatch.com.mrliuframe.util;

import android.text.TextUtils;

/**
 * Created by justin on 16/12/6.
 */

public class StringFormatUtil {

    public static String getFormatOnePoint(String formatStr) {
        if (TextUtils.isEmpty(formatStr)) {
            return "0.0";
        }
        try {
            return String.format("%.1f", Float.valueOf(formatStr));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return formatStr;
        }
    }

}
