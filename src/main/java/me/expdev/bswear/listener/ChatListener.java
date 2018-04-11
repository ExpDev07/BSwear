package me.expdev.bswear.listener;

import me.expdev.bswear.BSwearPlugin;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChat(ChatEvent event) {
        // Handle event
        BSwearPlugin.getPlugin().getBadWords().handleEvent(event);
    }

}
