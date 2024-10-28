package de.saloking.FFA;

import org.bukkit.entity.Player;

public class OnSpawnIsland {
    private final Main plugin;
    public OnSpawnIsland(Main plugin){
        this.plugin = plugin;
    }

    public boolean isOnSpawnIsland(double x, double y, double z){
        int smallerX = smallerValue("X");
        int greaterX = greaterValue("X");
        int smallerY = smallerValue("Y");
        int greaterY = greaterValue("Y");
        int smallerZ = smallerValue("Z");
        int greaterZ = greaterValue("Z");


        return x >= smallerX && x < greaterX &&
                y > smallerY && y < greaterY &&
                z > smallerZ && z < greaterZ;
    }



    public int greaterValue(String path) {
        int a = this.plugin.getConfig().getInt("spawn-area."+path+"1");
        int b = this.plugin.getConfig().getInt("spawn-area."+path+"2");

        if(a > b) return a;
        return b;
    }
    public int smallerValue(String path){
        int a = this.plugin.getConfig().getInt("spawn-area."+path+"1");
        int b = this.plugin.getConfig().getInt("spawn-area."+path+"2");

        if(a <= b) return a;
        return b;
    }
}

