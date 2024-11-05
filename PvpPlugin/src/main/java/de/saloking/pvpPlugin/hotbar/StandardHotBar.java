package de.saloking.pvpPlugin.hotbar;

import de.saloking.pvpPlugin.item.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StandardHotBar implements PlayerHotBar {

    @Override
    public void putHotbarItems(Player player) {
        Inventory playerInventory = player.getInventory();
        playerInventory.clear();

        ItemStack uhcItem = new ItemBuilder(Material.GOLDEN_APPLE)
                .setDisplayName(
                        Component.text(NamedTextColor.GOLD + "UHC")
                )
                .build();

        ItemStack onlySwordItem = new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName(
                        Component.text(NamedTextColor.BLUE + "Only Sword")
                )
                .build();

        ItemStack axt = new ItemBuilder(Material.IRON_AXE)
                .setDisplayName(
                        Component.text(NamedTextColor.WHITE + "Axt-Pvp")
                )
                .build();

        ItemStack customInventoryItem = new ItemBuilder(Material.PHANTOM_MEMBRANE)
                .setDisplayName(
                        Component.text(NamedTextColor.RED + "Selbsteinstellbar")
                )
                .build();

        playerInventory.setItem(0, uhcItem);
        playerInventory.setItem(1, onlySwordItem);
        playerInventory.setItem(2, axt);
        playerInventory.setItem(8, customInventoryItem);
    }
}
