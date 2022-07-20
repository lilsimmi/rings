package me.luckymutt.rw.config;

import me.luckymutt.rw.RingsReworked;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BaseConfig {

    private final Plugin plugin;
    private final File file;
    private YamlConfiguration configuration;

    public BaseConfig(String path) {
        this.plugin = JavaPlugin.getProvidingPlugin(RingsReworked.class);
        this.file = new File(plugin.getDataFolder() + "/" + path);
    }

    public BaseConfig copy(String copyPath) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                Files.copy(
                        plugin.getResource(copyPath),
                        file.toPath()
                );
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        return this;
    }

    public BaseConfig load() {
        this.configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().copyDefaults();

        return this;
    }

    public BaseConfig set(String path, Object o) {
        configuration.set(path, o);
        save();

        return this;
    }

    public ConfigObject get(String path) {
        return new ConfigObject(configuration, path);
    }

    private void save() {
        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
