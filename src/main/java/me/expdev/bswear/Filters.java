package me.expdev.bswear;

import com.sun.xml.internal.messaging.saaj.soap.MessageFactoryImpl;
import me.expdev.bswear.filter.MessageFilter;
import me.expdev.bswear.utils.StringUtils;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Filters {

    private List<MessageFilter> filters = new ArrayList<MessageFilter>();

    public Filters() {

    }

    public void add(MessageFilter filter) {
        filters.add(filter);
        System.out.println("Applied a new filter: [" + filter.getClass().getSimpleName() + "]");
    }

    public List<MessageFilter> getFilters() {
        return new ArrayList<MessageFilter>(this.filters);
    }

    /**
     * Handles a chat event
     *
     * @param event Event to handle
     */
    public void handleEvent(ChatEvent event) {
        String message = event.getMessage();

        // Overlap/apply filters
        for (MessageFilter filter : filters) {
            message = filter.filter(message);
        }
        event.setMessage(message);
    }

}
