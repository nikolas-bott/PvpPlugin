package de.saloking.pvpPlugin.listeners;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener {

    public PlayerDeathListener(PvpPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onPlayerDeath(PlayerDeathEvent event) {
                Player deceased = event.getPlayer();

                if (!plugin.getMatchService().isPlayerInMatch(deceased))
                    return;

                plugin.getMatchService().handleMatchEnd(deceased);
            }
        }, plugin);
    }
}
