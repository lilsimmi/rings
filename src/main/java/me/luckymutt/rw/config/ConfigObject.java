package me.luckymutt.rw.config;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class ConfigObject {

    private final YamlConfiguration configuration;
    private final String path;

    public ConfigObject(YamlConfiguration configuration, String path) {
        this.configuration = configuration;
        this.path = path;
    }

    public Object asObject() {
        return configuration.get(path);
    }

    public boolean asBoolean() {
        return configuration.getBoolean(path);
    }

    public Color asColor() {
        return configuration.getColor(path, Color.WHITE);
    }

    public ConfigurationSection asConfigurationSection() {
        return configuration.getConfigurationSection(path);
    }

    public double asDouble() {
        return configuration.getDouble(path, 0D);
    }

    public int asInteger() {
        return configuration.getInt(path, 0);
    }

    public ItemStack asItemStack() {
        return configuration.getItemStack(path, new ItemStack(Material.AIR));
    }

    public long asLong() {
        return configuration.getLong(path, 0L);
    }

    public Vector asVector() {
        return configuration.getVector(path, new Vector(0, 0, 0));
    }

    public String asString() {
        return configuration.getString(path, "");
    }

    public List<String> asStringList() {
        return configuration.getStringList(path);
    }

}
