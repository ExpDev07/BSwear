package me.expdev.bswear;

import me.expdev.bswear.listener.ChatListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class BSwearPlugin extends Plugin {

    private static BSwearPlugin i = null;

    public static BSwearPlugin getPlugin() {
        return i;
    }

    // START

    private BadWords badWords;

    @Override
    public void onLoad() {
        i = this;
    }

    @Override
    public void onEnable() {
        // Ensure data folder exists and load words from dictionary
        getDataFolder().mkdirs();
        loadWords();

        // Register listeners
        getProxy().getPluginManager().registerListener(this, new ChatListener());
    }

    @Override
    public void onDisable() {

    }

    private void loadWords() {
        File dic = new File(getDataFolder(), "dictionary.csv");
        try {
            this.badWords = new BadWords().populate(dic);
        } catch (IOException e) {
            getLogger().severe("Could not load dictionary of bad words!: " + e.getMessage());
        }
    }

    public BadWords getBadWords() {
        return badWords;
    }
}
