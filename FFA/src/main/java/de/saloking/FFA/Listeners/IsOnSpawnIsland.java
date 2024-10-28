package de.saloking.FFA.Listeners;

import de.saloking.FFA.Main;
import de.saloking.FFA.OnSpawnIsland;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class IsOnSpawnIsland implements Listener {
    private final OnSpawnIsland onSpawnIsland;
    public IsOnSpawnIsland(Main plugin){
        this.onSpawnIsland = new OnSpawnIsland(plugin);
    }

    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player p){
            if(onSpawnIsland.isOnSpawnIsland(p.getX(),p.getY(),p.getZ())){
                e.setCancelled(true);
            }
        }

    }

}
