package me.simmi.ringsv2.commands;

import me.simmi.ringsv2.manager.RingManager;
import me.simmi.ringsv2.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RingListCommand implements CommandExecutor {

    private final RingManager ringManager;

    public RingListCommand(RingManager ringManager){

        this.ringManager = ringManager;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player p)){
            sender.sendMessage(Utils.chat("&6speed2, haste2, nvision, saturation, flight, health, health2, jumpboost, invis, strength"));
            return true;
        }

        p.sendMessage(Utils.chat("&6speed2, haste2, nvision, saturation, flight, health, health2, jumpboost, invis, strength"));

        return true;
    }

}
