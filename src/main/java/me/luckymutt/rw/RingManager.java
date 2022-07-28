package me.luckymutt.rw;

import me.luckymutt.rw.config.BaseConfig;
import me.luckymutt.rw.config.ConfigManager;
import me.luckymutt.rw.event.RingEquipEvent;
import me.luckymutt.rw.event.RingUnequipEvent;
import me.luckymutt.rw.utility.ItemUtility;
import me.luckymutt.rw.utility.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class RingManager implements Listener {

    private final Plugin plugin;
    private final ConfigManager configManager;
    private final Map<String, Ring> ringMap;

    public RingManager(RingsReworked plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.ringMap = new HashMap<>();

        registerRings();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (event.getSlot() != 40) return;

            ItemStack slotItem = event.getCurrentItem();
            ItemStack cursorItem = event.getCursor();
            for (String ringKey : ringMap.keySet()) {
                Ring ring = getRing(ringKey);

                if (cursorItem.equals(ring.getRingItem())) activateRing(ring, player);
                if (slotItem.equals(ring.getRingItem())) deactivateRing(ring, player);
            }
        }
    }

    @EventHandler
    public void onItemSwapEvent(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack offHandItem = event.getOffHandItem();
        ItemStack mainHandItem = event.getMainHandItem();

        for (String ringKey : ringMap.keySet()) {
            Ring ring = getRing(ringKey);

            if (offHandItem.equals(ring.getRingItem())) activateRing(ring, player);
            if (mainHandItem.equals(ring.getRingItem())) deactivateRing(ring, player);
        }
    }

    private void activateRing(Ring ring, Player player) {
        if (!player.hasPermission("rings.use.*") || !player.hasPermission(ring.getUsePermission())) return;

        RingEquipEvent ringEquipEvent = new RingEquipEvent(player, ring);
        Bukkit.getPluginManager().callEvent(ringEquipEvent);

        if (!ringEquipEvent.isCancelled()) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.3f, 1f);

            ring.getPotions().forEach((potionEffectType, integer) ->
                    player.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, integer, false, false, true)));

            ring.getAttributes().forEach((attribute, value) ->
                    player.getAttribute(attribute).setBaseValue(value));
        }
    }

    private void deactivateRing(Ring ring, Player player) {
        RingUnequipEvent ringUnequipEvent = new RingUnequipEvent(player, ring);
        Bukkit.getPluginManager().callEvent(ringUnequipEvent);

        if (!ringUnequipEvent.isCancelled()) {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 0.3f, 1f);

            ring.getPotions().forEach((potionEffectType, integer) ->
                    player.removePotionEffect(potionEffectType));

            ring.getAttributes().forEach((attribute, value) ->
                    player.getAttribute(attribute).setBaseValue(player.getAttribute(attribute).getDefaultValue()));
        }
    }

    @EventHandler
    public void onCraftEvent(PrepareItemCraftEvent event) {
        Optional<HumanEntity> crafter = event.getViewers().stream()
                .filter(humanEntity -> humanEntity instanceof Player)
                .findFirst();
        if (crafter.isEmpty()) return;

        Player player = (Player) crafter.get();
        Recipe recipe = event.getRecipe();

        if (recipe == null) return;

        ItemStack result = recipe.getResult();

        for (String ringKey : ringMap.keySet()) {
            Ring ring = getRing(ringKey);

            if (ring.getRingItem().equals(result)) {
                if (!player.hasPermission("rings.craft.*") || !player.hasPermission(ring.getCraftPermission())) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                }
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Optional<Ring> ring = getRing(player.getInventory().getItemInOffHand());

        ring.ifPresent(value -> activateRing(value, player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Optional<Ring> ring = getRing(player.getInventory().getItemInOffHand());

        ring.ifPresent(value -> deactivateRing(value, player));
    }

    private void registerRings() {
        BaseConfig config = configManager.getConfig("main");
        ConfigurationSection ringsSection = config.get("Rings").asConfigurationSection();

        for (String ringKey : ringsSection.getKeys(false)) {
            ConfigurationSection ringSection = ringsSection.getConfigurationSection(ringKey);

            String usePermission = ringSection.getString("Permission");

            ConfigurationSection ringCraftingSection = ringSection.getConfigurationSection("Crafting");
            String craftPermission = ringCraftingSection.getString("Permission");
            List<String> craftShape = ringCraftingSection.getStringList("Shape");
            Map<String, Material> craftingMaterials = new HashMap<>();

            ConfigurationSection craftingMaterialSection = ringCraftingSection.getConfigurationSection("Materials");
            for (String materialKey : craftingMaterialSection.getKeys(false)) {
                craftingMaterials.put(materialKey, Material.getMaterial(craftingMaterialSection.getString(materialKey)));
            }

            ConfigurationSection craftingResultSection = ringCraftingSection.getConfigurationSection("Result");
            ItemUtility itemCreator = ItemUtility.of(Material.valueOf(craftingResultSection.getString("Material")));
            itemCreator.with((itemStack, itemMeta) -> itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS));

            if (craftingResultSection.contains("Name")) {
                itemCreator.with((itemStack, itemMeta) ->
                        itemMeta.setDisplayName(Message.of(craftingResultSection.getString("Name")).getMessage())
                );
            }

            if (craftingResultSection.contains("Lore")) {
                itemCreator.with((itemStack, itemMeta) ->
                        itemMeta.setLore(Message.of(craftingResultSection.getStringList("Lore")).getMessageList())
                );
            }

            if (craftingResultSection.contains("Enchantments")) {
                ConfigurationSection enchantmentsSection = craftingResultSection.getConfigurationSection("Enchantments");
                for (String enchantmentKey : enchantmentsSection.getKeys(false)) {
                    itemCreator.with((itemStack, itemMeta) ->
                            itemMeta.addEnchant(
                                    Enchantment.getByKey(NamespacedKey.minecraft(enchantmentKey.toLowerCase())),
                                    enchantmentsSection.getInt(enchantmentKey, 1),
                                    true
                            )
                    );
                }
            }

            if (craftingResultSection.contains("CustomModelData")) {
                itemCreator.with((itemStack, itemMeta) ->
                        itemMeta.setCustomModelData(craftingResultSection.getInt("CustomModelData")));
            }

            registerCraftingRecipe(ringKey, craftShape, craftingMaterials, itemCreator.build());

            Map<PotionEffectType, Integer> potionMap = new HashMap<>();
            Map<Attribute, Double> attributeMap = new HashMap<>();

            if (ringSection.isConfigurationSection("Potions")) {
                ConfigurationSection potionSection = ringSection.getConfigurationSection("Potions");
                for (String potionKey : potionSection.getKeys(false)) {
                    potionMap.put(PotionEffectType.getByName(potionKey), potionSection.getInt(potionKey));
                }
            }

            if (ringSection.isConfigurationSection("Attributes")) {
                ConfigurationSection attributeSection = ringSection.getConfigurationSection("Attributes");
                for (String attributeKey : attributeSection.getKeys(false)) {
                    attributeMap.put(Attribute.valueOf(attributeKey), attributeSection.getDouble(attributeKey));
                }
            }

            ringMap.put(ringKey, new Ring(
                    usePermission,
                    craftPermission,
                    potionMap,
                    attributeMap,
                    itemCreator.build(),
                    new NamespacedKey(plugin, ringKey.toLowerCase())
            ));
        }
    }

    private void registerCraftingRecipe(String key, List<String> shape, Map<String, Material> materials, ItemStack result) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, key.toLowerCase()), result);
        recipe.shape(shape.toArray(new String[]{}));

        for (String materialKey : materials.keySet()) {
            recipe.setIngredient(materialKey.toCharArray()[0], materials.get(materialKey));
        }

        Bukkit.addRecipe(recipe);
    }

    public Map<String, Ring> getRingMap() {
        return ringMap;
    }

    public Set<String> getRingNames() {
        return ringMap.keySet();
    }

    public Ring getRing(String name) {
        return ringMap.getOrDefault(name, null);
    }

    public Optional<Ring> getRing(ItemStack itemStack) {
        return ringMap.values().stream().filter(ring -> ring.getRingItem().equals(itemStack)).findFirst();
    }

    public void unloadCraftingRecipes() {
        for (String ringKey : getRingNames()) {
            Ring ring = getRing(ringKey);
            Bukkit.removeRecipe(ring.getKey());
        }
    }
}
