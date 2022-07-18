package me.simmi.ringsv2.commands;

import me.simmi.ringsv2.items.Items;
import me.simmi.ringsv2.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveRingCommand implements CommandExecutor {

    String prefix = ChatColor.GOLD.toString()+ChatColor.BOLD.toString()+"Rings"+ChatColor.DARK_GRAY.toString()+ChatColor.BOLD.toString()+"> ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player player)){
            sender.sendMessage(Utils.chat("&4You cannot execute this command in the terminal!"));
            return true;
        }

        if (player.hasPermission("rings.give"))
        {
            if (args.length >= 1)
            {
                String ring = args[0];
                String playerName = args[1];
                Player p = Bukkit.getPlayer(playerName);
                if(p == null) return true;

                if (ring.equalsIgnoreCase("speed2"))
                {
                    p.sendMessage(prefix+ ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Speed II Ring");
                    p.getInventory().addItem(Items.speedRing);
                }
                else if (ring.equalsIgnoreCase("haste2"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Haste II Ring");
                    p.getInventory().addItem(Items.hasteRing);
                }
                else if (ring.equalsIgnoreCase("nvision"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Night Vision Ring");
                    p.getInventory().addItem(Items.visionRing);
                }
                else if (ring.equalsIgnoreCase("saturation"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Saturation Ring");
                    p.getInventory().addItem(Items.satRing);
                }

                else if (ring.equalsIgnoreCase("health"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Health Ring");
                    p.getInventory().addItem(Items.healthRing);
                }
                else if (ring.equalsIgnoreCase("health2"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Health T2 Ring");
                    p.getInventory().addItem(Items.healthRing2);
                }
                else if (ring.equalsIgnoreCase("jumpboost"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Jump Boost Ring");
                    p.getInventory().addItem(Items.jumpRing);
                }
                else if (ring.equalsIgnoreCase("invis"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Invisibility Ring");
                    p.getInventory().addItem(Items.invisRing);
                }
                else if (ring.equalsIgnoreCase("strength"))
                {
                    p.sendMessage(prefix+ChatColor.WHITE+"You've received the "+ChatColor.GRAY+"Strength Ring");
                    p.getInventory().addItem(Items.strengthRing);
                }
            }
            else
            {
                player.sendMessage(ChatColor.RED+"Please specify the following fields");
                player.sendMessage(ChatColor.RED+"/givering <ring> <player>");
                player.sendMessage(ChatColor.RED+"/ringlist for ring names");
            }
        }

        return true;
    }
}
