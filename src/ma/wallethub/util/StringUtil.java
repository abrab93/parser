/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.wallethub.util;

/**
 *
 * @author YounesSama
 */
public final class StringUtil {

    private static final String DOUBLE_QUOTE = "\"";
    private static final String BALCK_STRING = "";

    private StringUtil() {
    }

    public static String eliminateDoubleQuote(String text) {
        return text.replace(DOUBLE_QUOTE, BALCK_STRING);
    }

    public static boolean isNull(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNotNull(String s) {
        return !isNull(s);
    }
}
