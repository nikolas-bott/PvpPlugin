package de.saloking.FFA.service;

import de.saloking.FFA.FFAPlugin;

public class SpawnIslandService {

    private final FFAPlugin plugin;
    private int minX, maxX, minY, maxY, minZ, maxZ;

    public SpawnIslandService(FFAPlugin plugin) {
        this.plugin = plugin;
        initializeCoordinates();
    }

    private void initializeCoordinates() {
        minX = getBoundary("X", true);
        maxX = getBoundary("X", false);
        minY = getBoundary("Y", true);
        maxY = getBoundary("Y", false);
        minZ = getBoundary("Z", true);
        maxZ = getBoundary("Z", false);
    }

    public boolean isOnSpawnIsland(double x, double y, double z) {
        return isWithinBounds(x, minX, maxX) &&
                isWithinBounds(y, minY, maxY) &&
                isWithinBounds(z, minZ, maxZ);
    }

    private boolean isWithinBounds(double value, int min, int max) {
        return value >= min && value < max;
    }

    private int getBoundary(String axis, boolean isMin) {
        int first = plugin.getConfig().getInt("spawn-area." + axis + "1");
        int second = plugin.getConfig().getInt("spawn-area." + axis + "2");
        return isMin ? Math.min(first, second) : Math.max(first, second);
    }
}
