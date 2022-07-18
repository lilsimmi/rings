package me.simmi.ringsv2.items;

import me.simmi.ringsv2.manager.RingManager;
import me.simmi.ringsv2.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {
    RingManager ringManager;
    public Items(RingManager ringManager){
        this.ringManager = ringManager;
    }

    public static ItemStack speedRing = new ItemStack(Material.IRON_NUGGET); //done
    public static ItemMeta speedMeta = speedRing.getItemMeta();
    public static ItemStack hasteRing = new ItemStack(Material.GOLD_NUGGET); //done
    public static ItemMeta hasteMeta = hasteRing.getItemMeta();
    public static ItemStack visionRing = new ItemStack(Material.BLAZE_POWDER); //done
    public static ItemMeta visionMeta = visionRing.getItemMeta();
    public static ItemStack satRing = new ItemStack(Material.SLIME_BALL); //
    public static ItemMeta satMeta = satRing.getItemMeta();
    public static ItemStack healthRing = new ItemStack(Material.RED_DYE);
    public static ItemMeta healthMeta = healthRing.getItemMeta();
    public static ItemStack healthRing2 = new ItemStack(Material.CYAN_DYE);
    public static ItemMeta health2Meta = healthRing2.getItemMeta();
    public static ItemStack jumpRing = new ItemStack(Material.YELLOW_DYE);
    public static ItemMeta jumpMeta = jumpRing.getItemMeta();
    public static ItemStack invisRing = new ItemStack(Material.LIME_DYE);
    public static ItemMeta invisMeta = invisRing.getItemMeta();
    public static ItemStack strengthRing = new ItemStack(Material.PINK_DYE);
    public static ItemMeta strengthMeta = strengthRing.getItemMeta();

    public void createItems(){

        buildItem("Speed II Ring", speedRing, speedMeta);
        buildItem("Haste II Ring", hasteRing, hasteMeta);
        buildItem("Night Vision Ring", visionRing, visionMeta);
        buildItem("Saturation Ring", satRing, satMeta);
        buildItem("Health Ring", healthRing, healthMeta);
        buildItem("Health T2 Ring", healthRing2, health2Meta);
        buildItem("Jump Boost II Ring", jumpRing, jumpMeta);
        buildItem("Invisibility Ring", invisRing,  invisMeta);
        buildItem("Strength II Ring", strengthRing, strengthMeta);

        ringManager.getRingMap().put(0, Items.speedRing);
        ringManager.getRingMap().put(1, Items.hasteRing);
        ringManager.getRingMap().put(2, Items.visionRing);
        ringManager.getRingMap().put(3, Items.satRing);
        ringManager.getRingMap().put(4, Items.jumpRing);
        ringManager.getRingMap().put(5, Items.invisRing);
        ringManager.getRingMap().put(6, Items.strengthRing);
        ringManager.getRingMap().put(7, Items.healthRing);
        ringManager.getRingMap().put(8, Items.healthRing2);
    }

    public void buildItem(String name, ItemStack item, ItemMeta meta){
        meta.setDisplayName(Utils.chat("&6"+name));
        List<String> lore = new ArrayList<>();
        lore.add(Utils.chat("&fHold in off-hand to gain effect"));
        meta.setLore(lore);
        item.setItemMeta(meta);


    }

}
