package de.saloking.pvpPlugin;

import de.saloking.pvpPlugin.Commands.AxeTP;
import de.saloking.pvpPlugin.Commands.JoinInventory;
import de.saloking.pvpPlugin.Commands.UHCTP;
import de.saloking.pvpPlugin.Commands.ostp;
import de.saloking.pvpPlugin.Events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvpPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic


        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractPlayer(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(),this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerEat(),this);

        getCommand("openInv").setExecutor(new JoinInventory());
        getCommand("uhctp").setExecutor(new UHCTP(this));
        getCommand("axetp").setExecutor(new AxeTP(this));
        getCommand("ostp").setExecutor(new ostp(this));


    }
}
