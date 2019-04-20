package br.com.petrim.lich.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * Get process date today.
     *
     * @return process date today
     */
    public static Date getProcessDateToday() {
        String dateS = getStringFromDate(new Date(), "dd/MM/yyyy");
        return getDateFromString(dateS, "dd/MM/yyyy");
    }

    /**
     * Get date from string.
     *
     * @param dateS date s
     * @param pattern pattern
     * @return date from string
     */
    public static Date getDateFromString(String dateS, String pattern) {
        Date dateD = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            dateD = sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateD;
    }

    /**
     * Get string from date.
     *
     * @param dateD date d
     * @param pattern pattern
     * @return string from date
     */
    public static String getStringFromDate(Date dateD, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dateD);
    }

    /**
     * Get date from date.
     *
     * @param dateD date d
     * @param pattern pattern
     * @return string from date
     */
    public static Date getDateFromDate(Date dateD, String pattern) {
        String dateS = getStringFromDate(dateD, pattern);
        return getDateFromString(dateS, pattern);
    }

}
