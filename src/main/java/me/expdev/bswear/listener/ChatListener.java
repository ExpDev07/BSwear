package me.expdev.bswear.listener;

import me.expdev.bswear.Filters;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ChatListener implements Listener {

    private Filters filters;

    public ChatListener(Filters filters) {
        this.filters = filters;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChat(ChatEvent event) {
        // Do not! handle commands
        if (isCommand(event.getMessage())) {
            return;
        }

        // Handle event
        filters.handleEvent(event);
    }

    /**
     * Character used for executing commands
     */
    private static final char COMMAND_CHAR = '/';

    /**
     * @param text Text to check
     * @return True if text is considered a "command"
     */
    private static boolean isCommand(String text) {
        if (text == null || text.isEmpty()) return false;
        return text.charAt(0) == COMMAND_CHAR;
    }

}
