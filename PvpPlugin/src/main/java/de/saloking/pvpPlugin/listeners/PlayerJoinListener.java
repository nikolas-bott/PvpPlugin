package de.saloking.pvpPlugin.listeners;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener {

    public PlayerJoinListener(PvpPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onPlayerJoin(PlayerJoinEvent event) {
                event.getPlayer().sendMessage("Hallo FUNZT");

                plugin.getPlayerHotbar().putHotbarItems(event.getPlayer());
                event.setJoinMessage(event.getPlayer() + " hat die Arenen betreten.");
            }
        }, plugin);
    }
}
