package me.simmi.ringsv2;

import me.simmi.ringsv2.commands.GiveRingCommand;
import me.simmi.ringsv2.commands.RingListCommand;
import me.simmi.ringsv2.commands.RingsCommand;
import me.simmi.ringsv2.items.Items;
import me.simmi.ringsv2.listeners.OnClick;
import me.simmi.ringsv2.listeners.OnOffHandEvent;
import me.simmi.ringsv2.manager.RingManager;
import me.simmi.ringsv2.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Rings extends JavaPlugin implements Listener {

    RingManager ringManager;
    Items items;


    public void onEnable() {

        //Speed
        ShapedRecipe speed = createRingRecipe(Material.IRON_NUGGET, Utils.chat("&6Speed II Ring"), "iron_nugget");

        //Haste
        ShapedRecipe haste = createRingRecipe(Material.GOLD_NUGGET, Utils.chat("&6Haste II Ring"),"gold_nugget");

        //Night Vision
        ShapedRecipe nv = createRingRecipe(Material.BLAZE_POWDER, Utils.chat("&6Night Vision Ring"), "blaze_powder");

        //Saturation
        ShapedRecipe saturation = createRingRecipe(Material.SLIME_BALL, Utils.chat("&6Saturation Ring"), "slime_ball");

        //Health
        ShapedRecipe health = createRingRecipe(Material.RED_DYE, Utils.chat("&6Health Ring"), "red_dye");

        //Health T2
        ShapedRecipe health2 = createRingRecipe(Material.CYAN_DYE, Utils.chat("&6Health T2 Ring"), "cyan_dye");

        //Jump Boost II
        ShapedRecipe jumpboost2 = createRingRecipe(Material.YELLOW_DYE, Utils.chat("&6Jump Boost II Ring"), "yellow_dye");

        //Invisibility
        ShapedRecipe invisibility = createRingRecipe(Material.LIME_DYE, Utils.chat("&6Invisibility Ring"), "lime_dye");

        //Strength II
        ShapedRecipe strength2 = createRingRecipe(Material.PINK_DYE, Utils.chat("&6Strength II Ring"), "pink_dye");

        //Instances
        ringManager = new RingManager(this);
        items = new Items(ringManager);
        items.createItems();

        //Events
        getServer().getPluginManager().registerEvents(new OnOffHandEvent(), this);
        getServer().getPluginManager().registerEvents(new OnClick(this), this);

        //Recipes

        Bukkit.addRecipe(speed);
        Bukkit.addRecipe(haste);
        Bukkit.addRecipe(nv);
        Bukkit.addRecipe(saturation);
        Bukkit.addRecipe(health);
        Bukkit.addRecipe(health2);
        Bukkit.addRecipe(jumpboost2);
        Bukkit.addRecipe(invisibility);
        Bukkit.addRecipe(strength2);

        //Commands
        getCommand("givering").setTabCompleter(new tabList());
        getCommand("ringlist").setExecutor(new RingListCommand(ringManager));
        getCommand("rings").setExecutor(new RingsCommand(ringManager));
        getCommand("givering").setExecutor(new GiveRingCommand());

    }

    public ShapedRecipe createRingRecipe(Material material, String displayName, String namespaceKeyName){

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        List<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&fHold in off-hand to gain effect"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, namespaceKeyName);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("iii", "ini", "iii");
        recipe.setIngredient('n', Material.NETHER_STAR);
        recipe.setIngredient('i', material);

        return recipe;
    }

}