package me.luckymutt.rw;

import me.luckymutt.rw.commands.RingCommand;
import me.luckymutt.rw.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
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

        RingCommand ringCommand = new RingCommand(this);
        registerCommandWithTabComplete("rings", ringCommand, ringCommand);
    }

    @Override
    public void onDisable() {
        ringManager.unloadCraftingRecipes();
    }

    private void registerCommandWithTabComplete(String commandName, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        PluginCommand command = getCommand(commandName);
        command.setExecutor(commandExecutor);
        command.setTabCompleter(tabCompleter);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public RingManager getRingManager() {
        return ringManager;
    }

}
