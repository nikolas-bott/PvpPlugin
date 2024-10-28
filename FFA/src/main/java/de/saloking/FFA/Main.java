package de.saloking.FFA;

import de.saloking.FFA.Listeners.IsOnSpawnIsland;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //Config
        getConfig().options().copyDefaults(true);
        saveConfig();
        //Listeners
        getServer().getPluginManager().registerEvents(new IsOnSpawnIsland(this), this);
    }
}
