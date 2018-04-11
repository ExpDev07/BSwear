package me.expdev.bswear;

import me.expdev.bswear.filter.CapsFilter;
import me.expdev.bswear.filter.SwearFilter;
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

    private Filters filters;

    @Override
    public void onLoad() {
        i = this;
    }

    @Override
    public void onEnable() {
        // Ensure data folder exists and load words from dictionary
        getDataFolder().mkdirs();
        loadFilters();

        // Register listeners
        getProxy().getPluginManager().registerListener(this, new ChatListener());
    }

    @Override
    public void onDisable() {

    }

    private void loadFilters()  {
        // Create a instance of filters
        this.filters = new Filters();

        File dic = new File(getDataFolder(), "dictionary.csv");
        try {
            // Apply default filters
            filters.add(new SwearFilter().populate(dic));
            filters.add(new CapsFilter());
        } catch (IOException e) {
            getLogger().severe("Could not load dictionary of bad words!: " + e.getMessage());
            getLogger().severe("This means that no default filters will be loaded");
        }
    }

    public Filters getFilters() {
        return filters;
    }
}
