package de.saloking.pvpPlugin;


import de.saloking.pvpPlugin.Commands.JoinInventory;

import de.saloking.pvpPlugin.Events.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.A;

public final class PvpPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic


        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractPlayer(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(),this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerEat(),this);

        getCommand("openInv").setExecutor(new JoinInventory());
           }
}
