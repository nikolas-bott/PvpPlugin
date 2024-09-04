package de.saloking.pvpPlugin.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener{
    @EventHandler
    public void onPlayerDamager(EntityDamageEvent e){
        //e.setCancelled(true);
    }
}
