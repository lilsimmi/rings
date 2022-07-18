package me.simmi.ringsv2.manager;

import me.simmi.ringsv2.Rings;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RingManager {

    Inventory rings = Bukkit.createInventory(null, 27, "Rings");
    Map<Integer, ItemStack> ringMap = new HashMap<>();

    Rings instance;

    public RingManager(Rings instance){
        this.instance = instance;
    }

    public Inventory getRingInventory(){
        return rings;
    }

    public Map<Integer, ItemStack> getRingMap(){
        return ringMap;
    }

}
