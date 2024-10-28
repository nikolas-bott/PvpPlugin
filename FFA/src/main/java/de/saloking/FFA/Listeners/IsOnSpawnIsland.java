package de.saloking.FFA.Listeners;

import de.saloking.FFA.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class IsOnSpawnIsland implements Listener {
    private final Main plugin;
    public IsOnSpawnIsland(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e){
        int smallerX = smallerValue("X");
        int greaterX = greaterValue("X");
        int smallerY = smallerValue("Y");
        int greaterY = greaterValue("Y");
        int smallerZ = smallerValue("Z");
        int greaterZ = greaterValue("Z");

        e.getDamager().sendMessage("Test \n " +
                "X-Werte: "+smallerX +" / "+greaterX +"\n"+
                "Y_Werte: "+ smallerY + " / " + greaterY +"\n"+
                "Z-Werte: "+smallerZ +" / "+ greaterZ);


        if(e.getDamager() instanceof Player p){
            if(p.getX() >= smallerX && p.getX() < greaterX &&
                    p.getY() > smallerY && p.getY() < greaterY &&
                    p.getZ() > smallerZ && p.getZ() < greaterZ){
                e.setCancelled(true);
                e.getDamager().sendMessage("Test2");
            }
        }

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
