package me.simmi.ringsv2.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnOffHandEvent implements Listener {

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onOffHand(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        p.getInventory().getItemInOffHand();

        if(e.getOffHandItem() == null && e.getOffHandItem().getItemMeta() == null) return;

        if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.IRON_NUGGET)//speed
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.SPEED, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.GOLD_NUGGET)//haste
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            //add potion effect
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.BLAZE_POWDER)//nvision
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            //add potion effect
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.SLIME_BALL)//saturation
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.SATURATION, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);

            //add potion effect
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.RED_DYE)//saturation
        {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            p.setMaxHealth(24);
            p.setHealth(p.getMaxHealth());
            //add potion effect
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.YELLOW_DYE)//saturation
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.JUMP, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.LIME_DYE)//saturation
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.PINK_DYE)//saturation
        {
            p.addPotionEffect((new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 1)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
        }
        else if(e.getOffHandItem().hasItemMeta() && e.getOffHandItem().getType() == Material.CYAN_DYE)//saturation
        {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 3, 1);
            p.setMaxHealth(28);
            p.setHealth(p.getMaxHealth());
            //add potion effect
        }

        if(e.getMainHandItem().getType().equals(Material.SLIME_BALL))
        {
            p.removePotionEffect(PotionEffectType.SATURATION);
        }
        if(e.getMainHandItem().getType().equals(Material.BLAZE_POWDER))
        {
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
        if(e.getMainHandItem().getType().equals(Material.GOLD_NUGGET))
        {
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
        }
        if(e.getMainHandItem().getType().equals(Material.IRON_NUGGET))
        {
            p.removePotionEffect(PotionEffectType.SPEED);
        }
        if(e.getMainHandItem().getType().equals(Material.RABBIT_FOOT))
        {
            p.setAllowFlight(false);
        }
        if(e.getMainHandItem().getType().equals(Material.RED_DYE))
        {
            p.setMaxHealth(20);
            p.setHealth(p.getMaxHealth());
        }
        if(e.getMainHandItem().getType().equals(Material.YELLOW_DYE))
        {
            p.removePotionEffect(PotionEffectType.JUMP);
        }
        if(e.getMainHandItem().getType().equals(Material.LIME_DYE))
        {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        if(e.getMainHandItem().getType().equals(Material.PINK_DYE))
        {
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
        if(e.getMainHandItem().getType().equals(Material.CYAN_DYE))
        {
            p.setMaxHealth(20);
            p.setHealth(p.getMaxHealth());
        }


    }

}
