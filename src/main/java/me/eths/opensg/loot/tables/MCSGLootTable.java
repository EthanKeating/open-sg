package me.eths.opensg.loot.tables;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MCSGLootTable {

    public int getAverageItemCount() {
        return 6;
    }

    private final List<ItemStack> tier1Items = new ArrayList<ItemStack>(){{
        add(new ItemStack(Material.STONE_SWORD));
        add(new ItemStack(Material.WOOD_SWORD));
        add(new ItemStack(Material.STONE_AXE));
        add(new ItemStack(Material.WOOD_AXE));

        add(new ItemStack(Material.LEATHER_HELMET));
        add(new ItemStack(Material.LEATHER_CHESTPLATE));
        add(new ItemStack(Material.LEATHER_LEGGINGS));
        add(new ItemStack(Material.LEATHER_BOOTS));

        add(new ItemStack(Material.BOW));
        add(new ItemStack(Material.FISHING_ROD));
        add(new ItemStack(Material.ARROW, 5));

        add(new ItemStack(Material.IRON_INGOT));
        add(new ItemStack(Material.GOLD_INGOT));
        add(new ItemStack(Material.STICK));
        add(new ItemStack(Material.FEATHER, 5));
        add(new ItemStack(Material.FLINT, 2));

        add(new ItemStack(Material.BAKED_POTATO));
        add(new ItemStack(Material.APPLE));
        add(new ItemStack(Material.COOKIE, 2));
        add(new ItemStack(Material.CARROT));
        add(new ItemStack(Material.RAW_BEEF));
        add(new ItemStack(Material.RAW_CHICKEN));
        add(new ItemStack(Material.PORK));
        add(new ItemStack(Material.BREAD));
        add(new ItemStack(Material.RAW_FISH));
        add(new ItemStack(Material.MELON, 2));

    }};

    private final List<ItemStack> tier2Items = new ArrayList<ItemStack>(){{
        add(new ItemStack(Material.STONE_SWORD));

        add(new ItemStack(Material.IRON_HELMET));
        add(new ItemStack(Material.IRON_CHESTPLATE));
        add(new ItemStack(Material.IRON_LEGGINGS));
        add(new ItemStack(Material.IRON_BOOTS));

        add(new ItemStack(Material.CHAINMAIL_HELMET));
        add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
        add(new ItemStack(Material.CHAINMAIL_BOOTS));

        add(new ItemStack(Material.GOLD_HELMET));
        add(new ItemStack(Material.GOLD_CHESTPLATE));
        add(new ItemStack(Material.GOLD_LEGGINGS));
        add(new ItemStack(Material.GOLD_BOOTS));

        add(new ItemStack(Material.BOW));
        add(new ItemStack(Material.ARROW, 5));

        add(new ItemStack(Material.STICK));
        add(new ItemStack(Material.IRON_INGOT));
        add(new ItemStack(Material.GOLD_INGOT));
        add(new ItemStack(Material.DIAMOND));
        add(new ItemStack(Material.BOAT));
        add(new ItemStack(Material.FLINT_AND_STEEL));

        add(new ItemStack(Material.BAKED_POTATO));
        add(new ItemStack(Material.APPLE, 2));
        add(new ItemStack(Material.PUMPKIN_PIE, 2));
        add(new ItemStack(Material.GOLDEN_APPLE));
        add(new ItemStack(Material.GOLDEN_CARROT));
        add(new ItemStack(Material.COOKED_BEEF));
        add(new ItemStack(Material.COOKED_CHICKEN));
        add(new ItemStack(Material.GRILLED_PORK));
        add(new ItemStack(Material.MUSHROOM_SOUP));

    }};

}
