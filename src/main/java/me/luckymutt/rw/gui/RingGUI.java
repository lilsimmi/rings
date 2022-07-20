package me.luckymutt.rw.gui;

import me.luckymutt.rw.Ring;
import me.luckymutt.rw.RingManager;
import me.luckymutt.rw.RingsReworked;
import me.luckymutt.rw.utility.ItemUtility;
import me.luckymutt.rw.utility.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RingGUI implements Listener {

    private final RingManager ringManager;
    private final Inventory inventory;

    private final int ringAmount;

    private int page = 0;

    public RingGUI(RingsReworked plugin) {
        this.ringManager = plugin.getRingManager();
        this.inventory = Bukkit.createInventory(null, 54, Message.fromConfig("Gui.RingGui.Title").getMessage());

        this.ringAmount = ringManager.getRingMap().size();

        populateInventory();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private void populateInventory() {
        inventory.clear();

        for (int i = 0; i < 54; i++) {
            if (i == 10 || i == 19 || i == 28 || i == 37) i += 7;
            inventory.setItem(i, ItemUtility.of(Material.GRAY_STAINED_GLASS_PANE)
                    .with((itemStack, itemMeta) -> itemMeta.setDisplayName("§3"))
                    .build()
            );
        }

        addRingsToInventory();
    }

    private void addRingsToInventory() {
        for (int i = 0; i < 28; i++) {
            int currentPosition = i * (page + 1);
            Ring[] rings = ringManager.getRingMap().values().toArray(new Ring[]{});

            if (currentPosition < rings.length) inventory.addItem(rings[currentPosition].getRingItem());
        }
    }

    private void changePage(int newPage) {
        page = newPage;
        populateInventory();

        if (newPage > 0) inventory.setItem(47, ItemUtility.of(Material.PAPER)
                .with((itemStack, itemMeta) -> {
                    itemMeta.setDisplayName(Message.fromConfig("Gui.RingGui.PageLeft").placeholder("%page%", newPage - 1).getMessage());
                })
                .build()
        );

        if ((newPage + 1) * 28 < ringAmount) inventory.setItem(51, ItemUtility.of(Material.PAPER)
                .with((itemStack, itemMeta) -> {
                    itemMeta.setDisplayName(Message.fromConfig("Gui.RingGui.PageRight").placeholder("%page%", newPage + 1).getMessage());
                })
                .build()
        );
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory == null || !clickedInventory.equals(inventory)) return;

            int clickedSlot = event.getSlot();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem == null) return;

            if (clickedSlot == 47 && clickedItem.getType().equals(Material.PAPER)) changePage(page - 1);
            if (clickedSlot == 51 && clickedItem.getType().equals(Material.PAPER)) changePage(page + 1);

            if (!(clickedItem.getType().equals(Material.GRAY_STAINED_GLASS_PANE) || clickedItem.getType().equals(Material.PAPER)))
                player.getInventory().addItem(clickedItem);

            event.setCancelled(true);
        }
    }

}
