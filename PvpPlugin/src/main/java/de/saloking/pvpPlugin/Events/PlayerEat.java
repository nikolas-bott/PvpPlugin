package de.saloking.pvpPlugin.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerEat implements Listener {

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent e){
        e.setCancelled(true);
    }
}
