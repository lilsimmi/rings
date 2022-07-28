package me.luckymutt.rw;

import me.luckymutt.rw.commands.RingCommand;
import me.luckymutt.rw.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RingsReworked extends JavaPlugin {

    private ConfigManager configManager;
    private RingManager ringManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager();
        ringManager = new RingManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(ringManager, this);

        getCommand("rings").setExecutor(new RingCommand(this));
    }

    @Override
    public void onDisable() {
        ringManager.unloadCraftingRecipes();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public RingManager getRingManager() {
        return ringManager;
    }

}
