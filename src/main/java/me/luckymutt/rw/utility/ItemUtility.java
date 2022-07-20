package me.luckymutt.rw.utility;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class ItemUtility {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final List<BiConsumer<ItemStack, ItemMeta>> consumers;

    public ItemUtility(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();

        this.consumers = new ArrayList<>();
    }

    public ItemUtility with(BiConsumer<ItemStack, ItemMeta> consumer) {
        consumers.add(consumer);
        return this;
    }

    public ItemUtility withIf(
            BiConsumer<ItemStack, ItemMeta> consumer,
            BiPredicate<ItemStack, ItemMeta> predicate
    ) {
        if (predicate.test(itemStack, itemMeta)) consumers.add(consumer);
        return this;
    }

    public ItemUtility withIfElse(
            BiConsumer<ItemStack, ItemMeta> consumer,
            BiPredicate<ItemStack, ItemMeta> ifPredicate,
            BiConsumer<ItemStack, ItemMeta> elseConsumer
    ) {
        if (ifPredicate.test(itemStack, itemMeta)) consumers.add(consumer);
        else consumers.add(elseConsumer);
        return this;
    }

    public ItemStack build() {
        for (BiConsumer<ItemStack, ItemMeta> consumer : consumers) {
            consumer.accept(itemStack, itemMeta);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemUtility of(ItemStack itemStack) {
        return new ItemUtility(itemStack);
    }

    public static ItemUtility of(Material material) {
        return new ItemUtility(new ItemStack(material));
    }

}
