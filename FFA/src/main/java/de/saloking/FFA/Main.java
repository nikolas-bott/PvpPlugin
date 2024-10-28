package de.saloking.FFA;

import de.saloking.FFA.Listeners.DisableMiningOnSpawn;
import de.saloking.FFA.Listeners.DisablePvpOnSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //Config
        getConfig().options().copyDefaults(true);
        saveConfig();
        //Listeners
        getServer().getPluginManager().registerEvents(new DisablePvpOnSpawn(this), this);
        getServer().getPluginManager().registerEvents(new DisableMiningOnSpawn(this), this);
    }
}
