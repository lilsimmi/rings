package me.luckymutt.rw;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class Ring {

    private final String usePermission;
    private final String craftPermission;

    private final Map<PotionEffectType, Integer> potionMap;
    private final Map<Attribute, Double> attributeMap;

    private final ItemStack ringItem;

    private final NamespacedKey key;

    public final class RingRecipe {



    }

    public Ring(
            String usePermission,
            String craftPermission,
            Map<PotionEffectType, Integer> potionMap,
            Map<Attribute, Double> attributeMap,
            ItemStack ringItem,
            NamespacedKey key
    ) {
        this.usePermission = usePermission;
        this.craftPermission = craftPermission;

        this.potionMap = potionMap;
        this.attributeMap = attributeMap;

        this.ringItem = ringItem;
        this.key = key;
    }

    public Ring(String usePermission, String craftPermission, ItemStack ringItem, NamespacedKey key) {
        this.usePermission = usePermission;
        this.craftPermission = craftPermission;

        this.ringItem = ringItem;
        this.key = key;

        this.potionMap = new HashMap<>();
        this.attributeMap = new HashMap<>();
    }

    public void addPotion(PotionEffectType type, int amplifier) {
        potionMap.put(type, amplifier);
    }

    public void addAttribute(Attribute attribute, double value) {
        attributeMap.put(attribute, value);
    }

    public String getUsePermission() {
        return usePermission;
    }

    public String getCraftPermission() {
        return craftPermission;
    }

    public Map<PotionEffectType, Integer> getPotions() {
        return potionMap;
    }

    public Map<Attribute, Double> getAttributes() {
        return attributeMap;
    }

    public ItemStack getRingItem() {
        return ringItem;
    }

    public NamespacedKey getKey() {
        return key;
    }
}
