package de.saloking.FFA.listeners;

import de.saloking.FFA.FFAPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener {

    public EntityDamageByEntityListener(FFAPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
                if (!(event.getDamager() instanceof Player player))
                    return;

                if (!plugin.getSpawnIslandService().isOnSpawnIsland(player.getX(), player.getY(), player.getZ()))
                    return;

                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "Kämpfen, darf man nur unten in der Arena!");
            }
        }, plugin);
    }
}
