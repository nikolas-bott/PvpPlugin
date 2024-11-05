package de.saloking.pvpPlugin.listeners;

import de.saloking.pvpPlugin.PvpPlugin;
import de.saloking.pvpPlugin.match.MatchMode;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPreAttackEntityListener {

    public PlayerPreAttackEntityListener(PvpPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onPlayerAttackAttempt(PrePlayerAttackEntityEvent event) {
                Player player = event.getPlayer();

                if (!((event.getAttacked()) instanceof Player target))
                    return;

                MatchMode matchMode = plugin.getMatchService().getMatchModeFromItem(player.getItemInHand().getType());
                if (matchMode != null) {
                    plugin.getMatchService().initiateMatch(player, target, matchMode);
                }
            }
        }, plugin);
    }
}
