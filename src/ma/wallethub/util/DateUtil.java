/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YounesSama
 */
public class DateUtil {

    private static String PATTERN_YY_MM_DD_HH_MM_SS_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static String PATTERN_YY_MM_DD_HH = "yyyy-MM-dd HH";

    private DateUtil() {
    }

    public static String addDay(String date, int numberOfDay) {
        return format(addDay(parse(date), numberOfDay));
    }
    public static java.util.Date addDay(java.util.Date date, int numberOfDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numberOfDay);
        return c.getTime();
    }

    public static java.util.Date parseFormatHour(String date) {
        try {
            return parse(date, PATTERN_YY_MM_DD_HH);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static java.sql.Date parseSqlFormatHour(String date) {
        return toSqlDate(parseFormatHour(date));
    }

    public static java.util.Date parse(String date) {
        try {
            return parse(date, PATTERN_YY_MM_DD_HH_MM_SS_MS);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String formatFormatHour(java.util.Date date) {
        return format(date, PATTERN_YY_MM_DD_HH);
    }

    public static String format(java.util.Date date) {
        return format(date, PATTERN_YY_MM_DD_HH_MM_SS_MS);
    }

    public static java.util.Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(date);
    }

    public static String format(java.util.Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static java.sql.Date toSqlDate(java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public static java.util.Date toUtilDate(java.sql.Date sqlDate) {
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
    }
}
