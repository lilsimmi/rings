package me.simmi.ringsv2.commands;

import me.simmi.ringsv2.manager.RingManager;
import me.simmi.ringsv2.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RingsCommand implements CommandExecutor {

    private final RingManager ringManager;

    public RingsCommand(RingManager ringManager){
        this.ringManager = ringManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        if(!(sender instanceof Player p)){
            sender.sendMessage(Utils.chat("&4You cannot execute this command in the terminal!"));
            return true;
        }

        if(args.length == 0)
        {
            for (int x=0; x<=26; x++)
            {
                ringManager.getRingInventory().setItem(x, ringManager.getRingMap().getOrDefault(x, pane));
            }

            p.openInventory(ringManager.getRingInventory());

        }

        return true;
    }
}
