package de.saloking.pvpPlugin.Events;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDeath implements Listener {

    PlayerInteractPlayer match = new PlayerInteractPlayer(new PvpPlugin());

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

    }
}
