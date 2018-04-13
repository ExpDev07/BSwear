package me.expdev.bswear.filter;

import com.google.common.base.Charsets;
import me.expdev.bswear.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A filter to remove bad words
 *
 * <b>Note: a "word" can consist of multiple words as it is represented by a string</b>
 */
public class SwearFilter implements MessageFilter {

    private static final char STAR = '*';
    private Set<String> words = new HashSet<String>();

    public SwearFilter() {}

    public SwearFilter(List<String> initialWords) {
        populate(initialWords);
    }

    public SwearFilter(String... initialWords) {
        this(Arrays.asList(initialWords));
    }

    /**
     * @param word Word to add
     */
    public void add(String word) {
        words.add(word.toLowerCase());
    }

    /**
     * @param word Word to remove
     */
    public void remove(String word) {
        words.remove(word.toLowerCase());
    }

    /**
     * Populates the words from a file
     * <b>Note: words must have 1 line each</b>
     *
     * @param file File to use
     * @return Self
     * @throws IOException If lines can't be read
     */
    public SwearFilter populate(File file) throws IOException {
        return populate(Files.readAllLines(Paths.get(file.toURI()), Charsets.ISO_8859_1));
    }

    public SwearFilter populate(List<String> dictionary) {
        for (String word : dictionary) this.add(word);
        System.out.println("Populated swearing dictionary with " + dictionary.size() + " words");
        return this;
    }

    /**
     * Checks if a word is considered bad
     *
     * @param word Word to check
     * @return True if word is bad
     */
    public boolean isBad(String word) {
        return words.contains(word.toLowerCase());
    }

    /**
     * Filters a word. If found bad, replace with stars (*),
     * else will spit back word provided
     *
     * @param word Word to filter
     * @return Filtered word
     */
    private String filterWord(String word) {
        if (!(this.isBad(word))) return word;
        return StringUtils.replaceWithChar(word, STAR);
    }

    @Override
    public String filter(String message) {
        for (String word : words) {
            message = StringUtils.replace(message, word, this.filterWord(word));
        }
        return message;
    }

    @Override
    public FilterPriority getPriority() {
        return FilterPriority.NORMAL;
    }


}
