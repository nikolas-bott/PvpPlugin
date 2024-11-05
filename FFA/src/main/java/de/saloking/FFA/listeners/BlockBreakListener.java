package de.saloking.FFA.listeners;

import de.saloking.FFA.FFAPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener {

    public BlockBreakListener(FFAPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onBlockBreak(BlockBreakEvent event) {
                Player player = event.getPlayer();
                if (!plugin.getSpawnIslandService().isOnSpawnIsland(player.getX(), player.getY(), player.getZ()))
                    return;

                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Du darfst hier keine Blöcke abbauen!");
            }
        }, plugin);
    }
}
