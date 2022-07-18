package me.simmi.ringsv2.listeners;

import me.simmi.ringsv2.Rings;
import me.simmi.ringsv2.items.Items;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class OnClick implements Listener {

    private final Rings instance;

    public OnClick(Rings instance){
        this.instance = instance;
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onclick(InventoryClickEvent e)
    {
        HumanEntity player = e.getWhoClicked();
        ItemStack currentItem = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();
        String title = e.getView().getTitle(); //unused?

        if(currentItem == null) return;

        if (currentItem.getType().equals(Material.SLIME_BALL) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.SATURATION);
        }
        else if (currentItem.getType().equals(Material.BLAZE_POWDER) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
        else if (currentItem.getType().equals(Material.GOLD_NUGGET) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        }
        else if (currentItem.getType().equals(Material.IRON_NUGGET) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        else if (currentItem.getType().equals(Material.RED_DYE) && currentItem.hasItemMeta()) {
            p.setMaxHealth(20);
            p.setHealth(p.getMaxHealth());
        }
        else if (currentItem.getType().equals(Material.YELLOW_DYE) && currentItem.hasItemMeta()){
            player.removePotionEffect(PotionEffectType.JUMP);
        }
        else if (currentItem.getType().equals(Material.LIME_DYE) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        else if (currentItem.getType().equals(Material.PINK_DYE) && currentItem.hasItemMeta()) {
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
        else if (currentItem.getType().equals(Material.CYAN_DYE) && currentItem.hasItemMeta()) {
            p.setMaxHealth(20);
            p.setHealth(p.getMaxHealth());
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("rings"))
        {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            ItemStack clicked = e.getCurrentItem();
            if(clicked == null ) return;
            if (clicked.getType().equals(Material.AIR)) return;
            else if (clicked.getType().equals(Material.IRON_NUGGET) && p.hasPermission("rings.give"))//speed
            {
                p.getInventory().addItem(Items.speedRing);
            }
            else if (clicked.getType().equals(Material.GOLD_NUGGET) && p.hasPermission("rings.give")) //haste
            {
                p.getInventory().addItem(Items.hasteRing);

            }
            else if (clicked.getType().equals(Material.BLAZE_POWDER) && p.hasPermission("rings.give"))//nvision
            {
                p.getInventory().addItem(Items.visionRing);

            }
            else if (clicked.getType().equals(Material.SLIME_BALL) && p.hasPermission("rings.give"))//saturation
            {
                p.getInventory().addItem(Items.satRing);

            }
            else if (clicked.getType().equals(Material.RED_DYE) && p.hasPermission("rings.give"))//healthboost
            {
                p.getInventory().addItem(Items.healthRing);

            }
            else if (clicked.getType().equals(Material.YELLOW_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
                p.getInventory().addItem(Items.jumpRing);

            }
            else if (clicked.getType().equals(Material.LIME_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
                p.getInventory().addItem(Items.invisRing);

            }
            else if (clicked.getType().equals(Material.PINK_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
                p.getInventory().addItem(Items.strengthRing);

            }
            else if (clicked.getType().equals(Material.CYAN_DYE) && p.hasPermission("rings.give"))//jumpboost
            {
                p.getInventory().addItem(Items.healthRing2);

            }
        }

    }

}
