package de.saloking.pvpPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class JoinInventory implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player p){
            Inventory pInventory = p.getInventory();
            pInventory.clear();

            ItemStack uhc = new ItemStack(Material.GOLDEN_APPLE);

            ItemMeta uhcMeta = uhc.getItemMeta();
            uhcMeta.setDisplayName(ChatColor.GOLD+"UHC");
            uhc.setItemMeta(uhcMeta);

            pInventory.setItem(0,uhc);

            ItemStack onlySword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta onlySwordMeta = onlySword.getItemMeta();
            onlySwordMeta.setDisplayName(ChatColor.BLUE+"Only Sword");
            onlySword.setItemMeta(onlySwordMeta);

            pInventory.setItem(1,onlySword);

            ItemStack axt = new ItemStack(Material.IRON_AXE);
            ItemMeta axtMeta = onlySword.getItemMeta();
            axtMeta.setDisplayName(ChatColor.WHITE+"Axt-Pvp");
            axt.setItemMeta(axtMeta);

            pInventory.setItem(2,axt);

            ItemStack customInv = new ItemStack(Material.PHANTOM_MEMBRANE);
            ItemMeta customInvMeta = onlySword.getItemMeta();
            customInvMeta.setDisplayName(ChatColor.RED+"Selbsteinstellbar");
            customInv.setItemMeta(customInvMeta);

            pInventory.setItem(8,customInv);


        }

        return true;
    }
}
