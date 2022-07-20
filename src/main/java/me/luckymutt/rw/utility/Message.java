package me.luckymutt.rw.utility;

import me.luckymutt.rw.RingsReworked;
import me.luckymutt.rw.config.BaseConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Message {

    private final BaseConfig config;
    private List<String> strings;

    public Message() {
        RingsReworked plugin = (RingsReworked) JavaPlugin.getProvidingPlugin(RingsReworked.class);
        this.config = plugin.getConfigManager().getConfig("message");
        this.strings = new ArrayList<>();
    }

    public Message message(String message) {
        strings.add(message);
        return this;
    }

    public Message messageList(List<String> messageList) {
        strings.addAll(messageList);
        return this;
    }

    public Message messageFromConfig(String path) {
        strings.add(config.get(path).asString());
        return this;
    }

    public Message messageListFromConfig(String path) {
        strings.addAll(config.get(path).asStringList());
        return this;
    }

    public Message color() {
        strings = strings.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).toList();
        return this;
    }

    public Message placeholder(String placeholder, Object o) {
        strings = strings.stream().map(s -> s.replaceAll(placeholder, o.toString())).toList();
        return this;
    }

    public String getMessage() {
        return strings.get(0);
    }

    public List<String> getMessageList() {
        return strings;
    }

    public static Message of(String message) {
        return new Message().message(message).color();
    }

    public static Message of(List<String> messageList) {
        return new Message().messageList(messageList).color();
    }

    public static Message fromConfig(String path) {
        return new Message().messageFromConfig(path).color();
    }

    public static Message fromConfigList(String path) {
        return new Message().messageListFromConfig(path).color();
    }

    public static String prefix() {
        return new Message().messageFromConfig("Prefix").color().getMessage();
    }

}
