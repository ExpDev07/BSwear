package me.expdev.bswear.filter;

import me.expdev.bswear.utils.StringUtils;

/**
 * A filter to decrease use of excessive caps
 */
public class CapsFilter implements MessageFilter {

    private static final int CAPS_LIMIT = 5;

    @Override
    public String filter(String message) {
        StringBuilder sb = new StringBuilder();

        // Go through all literal words and make them lower case if
        // they exceed the caps limit
        for (String word : message.split(" ")) {
            if (StringUtils.countCaps(word) >= CAPS_LIMIT) sb.append(word.toLowerCase());
            else sb.append(word);

            sb.append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public FilterPriority getPriority() {
        return FilterPriority.NORMAL;
    }

}
