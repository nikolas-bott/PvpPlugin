package de.saloking.FFA.Listeners;

import de.saloking.FFA.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class IsOnSpawnIsland implements Listener {
    private final Main plugin;
    public IsOnSpawnIsland(Main plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e){
        int x1 = this.plugin.getConfig().getInt("spawn-area.X1");
        int x2 = this.plugin.getConfig().getInt("spawn-area.X2");
        int y1 = this.plugin.getConfig().getInt("spawn-area.Y1");
        int y2 = this.plugin.getConfig().getInt("spawn-area.Y2");
        int z1 = this.plugin.getConfig().getInt("spawn-area.Z1");
        int z2 = this.plugin.getConfig().getInt("spawn-area.Z2");

        if(e.getDamager() instanceof Player p){
            if(p.getX() > x1 && p.getX() < x2 &&
            p.getY() > y1 && p.getY() < y2 &&
            p.getZ() > z1 && p.getZ() < z2){
                e.setCancelled(true);
            }
        }

    }
}
