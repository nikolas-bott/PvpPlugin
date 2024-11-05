package de.saloking.pvpPlugin.listeners;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener {

    public EntityDamageListener(PvpPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onEntityDamage(EntityDamageEvent event) {
                //event.setCancelled(true);
            }
        }, plugin);
    }
}
