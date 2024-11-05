package de.saloking.FFA;

import de.saloking.FFA.listeners.BlockBreakListener;
import de.saloking.FFA.listeners.EntityDamageByEntityListener;
import de.saloking.FFA.service.SpawnIslandService;
import org.bukkit.plugin.java.JavaPlugin;

public final class FFAPlugin extends JavaPlugin {

    private SpawnIslandService spawnIslandService;

    @Override
    public void onEnable() {
        //Config
        getConfig().options().copyDefaults(true);
        saveConfig();

        //Listeners
        new BlockBreakListener(this);
        new EntityDamageByEntityListener(this);
    }

    public SpawnIslandService getSpawnIslandService() {
        return spawnIslandService;
    }
}
