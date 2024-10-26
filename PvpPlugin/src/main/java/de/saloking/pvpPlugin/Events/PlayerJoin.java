package de.saloking.pvpPlugin.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage("Hallo FUNZT");
        e.getPlayer().performCommand("openInv");
        e.setJoinMessage(e.getPlayer() + " hat die Arenen betreten.");
    }
}
