package de.saloking.pvpPlugin;


import de.saloking.pvpPlugin.commands.HotBarCommand;
import de.saloking.pvpPlugin.hotbar.PlayerHotBar;
import de.saloking.pvpPlugin.hotbar.StandardHotBar;
import de.saloking.pvpPlugin.listeners.*;
import de.saloking.pvpPlugin.service.MatchNotifyService;
import de.saloking.pvpPlugin.service.MatchService;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvpPlugin extends JavaPlugin {

    private PlayerHotBar playerHotbar;

    private MatchNotifyService matchNotifyService;
    private MatchService matchService;

    @Override
    public void onEnable() {
        matchNotifyService = new MatchNotifyService();
        matchService = new MatchService(this);

        playerHotbar = new StandardHotBar();

        new PlayerJoinListener(this);
        new EntityDamageListener(this);
        new PlayerJoinListener(this);
        new PlayerPreAttackEntityListener(this);

        new HotBarCommand(this);

        cancelEvents(PlayerItemConsumeEvent.class, InventoryClickEvent.class);
    }

    public PlayerHotBar getPlayerHotbar() {
        return playerHotbar;
    }

    public MatchNotifyService getMatchNotifyService() {
        return matchNotifyService;
    }

    public MatchService getMatchService() {
        return matchService;
    }

    @SafeVarargs
    public final void cancelEvents(final Class<? extends Event>... classes) {
        for (Class<? extends Event> aClass : classes) {
            this.getServer().getPluginManager().registerEvent(aClass, new Listener() {
            }, EventPriority.NORMAL, (listener, event) -> {
                if (!(event instanceof Cancellable cancellable))
                    return;

                cancellable.setCancelled(true);
            }, this);
        }
    }
}
