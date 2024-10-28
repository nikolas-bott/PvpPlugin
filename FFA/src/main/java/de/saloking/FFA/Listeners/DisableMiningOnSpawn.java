package de.saloking.FFA.Listeners;

import de.saloking.FFA.Main;
import de.saloking.FFA.OnSpawnIsland;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DisableMiningOnSpawn implements Listener {
    private final OnSpawnIsland onSpawnIsland;
    public DisableMiningOnSpawn(Main plugin){
        this.onSpawnIsland = new OnSpawnIsland(plugin);
    }

    @EventHandler
    public void onMine(BlockBreakEvent e){
        Player p = e.getPlayer();
            if(onSpawnIsland.isOnSpawnIsland(p.getX(),p.getY(),p.getZ())){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED+"Du darfst hier keine Blöcke abbauen!");
            }
    }
}
