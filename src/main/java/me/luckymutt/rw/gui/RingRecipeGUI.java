package me.luckymutt.rw.gui;

import me.luckymutt.rw.Ring;
import me.luckymutt.rw.RingsReworked;
import me.luckymutt.rw.utility.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

public class RingRecipeGUI implements Listener {

    private final Inventory inventory;
    private final Ring ring;

    public RingRecipeGUI(RingsReworked plugin, Ring ring) {
        this.inventory = Bukkit.createInventory(null, InventoryType.WORKBENCH, Message.fromConfig("Gui.RingRecipeGui.Title").placeholder("%ringname%", ring.getRingItem().getItemMeta().getDisplayName()).getMessage());
        this.ring = ring;

        populateInventory();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private void populateInventory() {
        ShapedRecipe recipe = (ShapedRecipe) Bukkit.getRecipe(ring.getKey());
        String[] shape = recipe.getShape();
        Map<Character, ItemStack> materials = recipe.getIngredientMap();

        int rowIndex = 1;
        for (String row : shape) {
            char[] chars = row.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                inventory.setItem((i + rowIndex), new ItemStack(materials.get(c)));
            }

            rowIndex += 3;
        }

        inventory.setItem(0, recipe.getResult());
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null || !clickedInventory.equals(inventory)) return;
        event.setCancelled(true);
    }

}
