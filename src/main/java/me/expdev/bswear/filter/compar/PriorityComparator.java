package me.expdev.bswear.filter.compar;

import me.expdev.bswear.filter.MessageFilter;

import java.util.Comparator;

/**
 * A comparator to compare filters
 */
public class PriorityComparator implements Comparator<MessageFilter> {

    @Override
    public int compare(MessageFilter o1, MessageFilter o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
