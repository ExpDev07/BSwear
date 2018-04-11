package me.expdev.bswear.utils;

public final class StringUtils {

    /**
     * Replaces all characters in string with provided char
     *
     * @param toReplace String to replace chars in
     * @param ch Character to replace with
     * @return Replaced string
     */
    public static String replaceWithChar(String toReplace, char ch) {
        StringBuilder sb = new StringBuilder();
        for (char c : toReplace.toCharArray()) {
            if (c == ' ') sb.append(" ");
            else sb.append(ch);
        }

        return sb.toString().trim();
    }

    public static String replace(String source, String target, String replacement) {
        StringBuilder sb = new StringBuilder(source);
        StringBuilder sbLower = new StringBuilder(source.toLowerCase());
        String searchString = target.toLowerCase();

        int idx = 0;
        while((idx = sbLower.indexOf(searchString, idx)) != -1) {
            sb.replace(idx, idx + searchString.length(), replacement);
            sbLower.replace(idx, idx + searchString.length(), replacement);
            idx+= replacement.length();
        }

        return sb.toString();
    }

    public static int countCaps(String word) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) count++;
        }

        return count;
    }

}
