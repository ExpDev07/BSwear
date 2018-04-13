package me.expdev.bswear.filter;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * A filter to fix simple grammar mistakes
 */
public class GrammarFilter implements MessageFilter {

    private static final Set<Character> CAPS_AFTER_CHARS = Sets.newHashSet('.', '?', '!');

    @Override
    public String filter(String message) {
        StringBuilder result = new StringBuilder(message.length());

        // First one is always capital!
        boolean capitalize = true;

        // Go through all the characters in the sentence.
        for (int i = 0; i < message.length(); i++) {
            // Get current char
            char c = message.charAt(i);

            // If it's a matching character then set next one to capital
            if(CAPS_AFTER_CHARS.contains(c)) {
                capitalize = true;
            }

            // If it's alphabetic character...
            else if(capitalize && Character.isAlphabetic(c)) {
                // ...we turn it to uppercase
                c = Character.toUpperCase(c);

                // Don't capitalize next characters
                capitalize = false;
            }

            // Accumulate in result
            result.append(c);
        }

        // Placing a dot at the end if no "special" character is there
        if (Character.isAlphabetic(message.charAt(message.toCharArray().length - 1))) {
            result.append(".");
        }

        return result.toString().trim();
    }

    @Override
    public FilterPriority getPriority() {
        return FilterPriority.NORMAL;
    }

}
