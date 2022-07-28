package me.luckymutt.rw.commands;

import me.luckymutt.rw.Ring;
import me.luckymutt.rw.RingManager;
import me.luckymutt.rw.RingsReworked;
import me.luckymutt.rw.gui.RingGUI;
import me.luckymutt.rw.gui.RingRecipeGUI;
import me.luckymutt.rw.utility.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RingCommand implements CommandExecutor, TabCompleter {

    private final RingsReworked plugin;
    private final RingManager ringManager;

    public RingCommand(RingsReworked plugin) {
        this.plugin = plugin;
        this.ringManager = plugin.getRingManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "gui" -> {
                    if (!hasPermission(sender, "rings.command.gui")) return true;
                    if (!isPlayer(sender)) return true;

                    RingGUI ringGUI = new RingGUI(plugin);
                    ringGUI.open((Player) sender);
                    return true;
                }
                case "list" -> {
                    if (!hasPermission(sender, "rings.command.list")) return true;

                    ComponentBuilder builder = new ComponentBuilder();
                    List<String> messageList = Message.fromConfigList("Command.List.ListLayout").getMessageList();

                    for (String string : messageList) {
                        if (string.contains("%list%")) {
                            for (String ringKey : ringManager.getRingNames()) {
                                Ring ring = ringManager.getRing(ringKey);

                                if (sender.hasPermission(ring.getUsePermission()))
                                    builder.append(new ComponentBuilder()
                                            .reset()
                                            .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rings recipe " + ringKey))
                                            .event(new HoverEvent(
                                                    HoverEvent.Action.SHOW_TEXT,
                                                    new Text(Message.fromConfig("Command.List.ListItem.Hover").getMessage())
                                            ))
                                            .append(Message.fromConfig("Command.List.ListItem.Name").placeholder("%name%", ringKey).getMessage())
                                            .create()
                                    );
                            }
                        } else {
                            builder.append(string);
                        }
                    }

                    sender.spigot().sendMessage(builder.create());
                    return true;
                }

                case "recipe" -> {
                    if (!hasPermission(sender, "rings.command.recipe")) return true;
                    if (!isPlayer(sender)) return true;

                    if (args.length != 2) {
                        sender.sendMessage(Message.prefix() + Message.fromConfig("Command.Recipe.NoRingName").getMessage());
                        return true;
                    }

                    Optional<String> ringName = getRingByName(sender, args[1]);
                    if (ringName.isEmpty()) return true;

                    Ring ring = ringManager.getRing(ringName.get());
                    RingRecipeGUI recipeGUI = new RingRecipeGUI(plugin, ring);
                    recipeGUI.open((Player) sender);
                    return true;
                }

                case "give" -> {
                    if (!hasPermission(sender, "rings.command.give")) return true;

                    if (args.length == 2) {
                        if (!isPlayer(sender)) return true;

                        Optional<String> ringName = getRingByName(sender, args[1]);
                        if (ringName.isEmpty()) return true;

                        Ring ring = ringManager.getRing(ringName.get());
                        ((Player) sender).getInventory().addItem(ring.getRingItem());

                        sender.sendMessage(Message.prefix() + Message.fromConfig("Command.Give.AddedRing").getMessage());
                        return true;
                    } else if (args.length == 3) {
                        Optional<String> ringName = getRingByName(sender, args[1]);
                        if (ringName.isEmpty()) return true;

                        Player target = Bukkit.getPlayerExact(args[2]);
                        if (target == null) {
                            sender.sendMessage(Message.prefix() + Message.fromConfig("Command.PlayerNotFound").getMessage());
                            return true;
                        }

                        Ring ring = ringManager.getRing(ringName.get());
                        target.getInventory().addItem(ring.getRingItem());

                        sender.sendMessage(
                                Message.prefix() +
                                Message.fromConfig("Command.Give.AddedRingToPlayer").placeholder("%player%", target.getName()).getMessage()
                        );
                        return true;
                    } else {
                        sender.sendMessage(Message.prefix() + Message.fromConfig("Command.Recipe.WrongUsage").getMessage());
                        return true;
                    }
                }

            }
        } else {
            sender.sendMessage(ChatColor.GRAY + "RingsReworked plugin | " + ChatColor.YELLOW + "By LuckyMutt");
        }

        return true;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) return true;
        else {
            sender.sendMessage(Message.prefix() + Message.fromConfig("NoPermission").getMessage());
            return false;
        }
    }

    private boolean isPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.prefix() + Message.fromConfig("Command.NoPlayer").getMessage());
            return false;
        } else return true;
    }

    private Optional<String> getRingByName(CommandSender sender, String name) {
        Optional<String> ringName = ringManager.getRingNames()
                .stream().filter(s -> s.equalsIgnoreCase(name)).findFirst();
        if (ringName.isEmpty()) {
            sender.sendMessage(Message.prefix() + Message.fromConfig("Command.RingNotFound").getMessage());
        }

        return ringName;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            if (sender.hasPermission("rings.command.gui")) list.add("gui");
            if (sender.hasPermission("rings.command.list")) list.add("list");
            if (sender.hasPermission("rings.command.recipe")) list.add("recipe");
            if (sender.hasPermission("rings.command.give")) list.add("give");
            if (sender.hasPermission("rings.command.reload")) list.add("reload");

            return list;
        }

        if (args.length >= 2) {
            switch (args[0]) {
                case "recipe" -> {
                    return ringManager.getRingNames().stream().toList();
                }
                case "give" -> {
                    if (args.length == 2) return ringManager.getRingNames().stream().toList();
                    if (args.length == 3) return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
                }
            }
        }

        return null;
    }

}
