package me.expdev.bswear;

import net.md_5.bungee.api.event.ChatEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Overview of bad words
 * <b>Note: a "word" can consist of multiple words as it is represented by a string</b>
 */
public class BadWords {

    private static final char STAR = '*';
    private Set<String> words = new HashSet<String>();

    public BadWords() {}

    public BadWords(String... initialWords) {
        words.addAll(Arrays.asList(initialWords));
    }

    /**
     * @param word Word to add
     */
    public void add(String word) {
        words.add(word);
    }

    /**
     * @param word Word to remove
     */
    public void remove(String word) {
        words.remove(word);
    }

    /**
     * Populates the words from a file
     * <b>Note: words must have 1 line each</b>
     *
     * @param file File to use
     * @return Self
     * @throws IOException If lines can't be read
     */
    public BadWords populate(File file) throws IOException {
        words.addAll(Files.readAllLines(Paths.get(file.toURI())));
        return this;
    }

    /**
     * Filters a word. If found bad, replace with stars (*),
     * else will spit back word provided
     *
     * @param word Word to filter
     * @return Filtered word
     */
    public String filter(String word) {
        if (!(this.isBad(word))) return word;
        return replaceWithChar(word, STAR);
    }

    /**
     * Checks if a word is considered bad
     *
     * @param word Word to check
     * @return True if word is bad
     */
    public boolean isBad(String word) {
        return words.contains(word);
    }

    /**
     * Handles a chat event
     *
     * @param event Event to handle
     */
    public void handleEvent(ChatEvent event) {
        StringBuilder builder = new StringBuilder();
        for (String word : event.getMessage().split(" ")) {
            builder.append(this.filter(word)).append(" ");
        }

        event.setMessage(builder.toString().trim());
    }

    /**
     * Replaces all characters in string with provided char
     *
     * @param toReplace String to replace chars in
     * @param ch Character to replace with
     * @return Replaced string
     */
    private static String replaceWithChar(String toReplace, char ch) {
        StringBuilder sb = new StringBuilder();
        for (char c : toReplace.toCharArray()) {
            if (c == ' ') sb.append(" ");
            else {
                sb.append(ch);
            }
        }

        return sb.toString().trim();
    }

}
