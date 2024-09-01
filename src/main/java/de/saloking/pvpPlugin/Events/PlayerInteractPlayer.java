package de.saloking.pvpPlugin.Events;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInteractPlayer implements Listener {
    HashMap<UUID,UUID> mapBothPlayer = new HashMap<>();
    HashMap<UUID,String> mapPlayer1 = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PrePlayerAttackEntityEvent e){

        Player p = e.getPlayer();

        if(e.getAttacked() instanceof Player player){
            if(p.getItemInHand().getType().equals(Material.GOLDEN_APPLE)){
                mapBothPlayer.put(player.getUniqueId(),p.getUniqueId());
                mapPlayer1.put(p.getUniqueId(),"UHC");

                if(mapBothPlayer.containsKey(player.getUniqueId()) && mapBothPlayer.containsKey(p.getUniqueId())){
                    if(mapPlayer1.get(player.getUniqueId()).equals(mapPlayer1.get(p.getUniqueId()))){
                        p.sendMessage("Du hast die Kampf im Modi UHC gegen "+player.getDisplayName()+" Akzeptiert!");
                        player.sendMessage(p.getDisplayName()+" hat den Kampf im Modi UHC akzeptiert!");
                        p.performCommand("UHCTP");
                        player.performCommand("UHCTP");
                        return;
                    }
                }
                player.sendMessage(ChatColor.BOLD+""+p.getDisplayName()+""+ChatColor.GOLD+" hat dich zu einem UHC Battle herausgefordert.");

              //  p.sendMessage("p="+p.getDisplayName()+" player="+player.getDisplayName());

                //player.sendMessage(uhcMsg);
            }else if(p.getItemInHand().getType().equals(Material.IRON_AXE)){
                mapBothPlayer.put(player.getUniqueId(),p.getUniqueId());
                mapPlayer1.put(p.getUniqueId(),"AXE");

                if(mapBothPlayer.containsKey(player.getUniqueId()) && mapBothPlayer.containsKey(p.getUniqueId())){
                    if(mapPlayer1.get(player.getUniqueId()).equals(mapPlayer1.get(p.getUniqueId()))){
                        p.sendMessage("Du hast den Kampf im Modi Axt-Pvp gegen "+player.getDisplayName()+" Akzeptiert!");
                        player.sendMessage(p.getDisplayName()+" hat den Kampf im Modi Axt-Pvp akzeptiert!");
                        p.performCommand("axetp");
                        player.performCommand("axetp");
                        return;
                    }
                }
                player.sendMessage(ChatColor.BOLD+""+p.getDisplayName()+""+ChatColor.WHITE+" hat dich zu einem Axt-Pvp Battle herausgefordert.");
                //player.sendMessage(axeMsg);
            } else if (p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                mapBothPlayer.put(player.getUniqueId(),p.getUniqueId());
                mapPlayer1.put(p.getUniqueId(),"OS");

                if(mapBothPlayer.containsKey(player.getUniqueId()) && mapBothPlayer.containsKey(p.getUniqueId())){
                    if(mapPlayer1.get(player.getUniqueId()).equals(mapPlayer1.get(p.getUniqueId()))){
                        p.sendMessage("Du hast die Kampf im Modi Only Sword gegen "+player.getDisplayName()+" Akzeptiert!");
                        player.sendMessage(p.getDisplayName()+" hat den Kampf im Modi Only Sword akzeptiert!");
                        p.performCommand("ostp");
                        player.performCommand("ostp");
                        return;
                    }
                }
                player.sendMessage(ChatColor.BOLD+""+p.getDisplayName()+""+ChatColor.BLUE+" hat dich zu einem Only Sword Battle herausgefordert.");
                //player.sendMessage(onlySwordMsg);
            }

        }
    }
}
 /*   Component uhcMsg = Component.text("[UHC AKZEPTIEREN]").hoverEvent(HoverEvent.showText(Component.text("Klicke um einen 1v1 Kamp im Modi UHC zu akzeptieren")))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p ~ 110 ~"));

        Component onlySwordMsg = Component.text("[Only Sword AKZEPTIEREN]").hoverEvent(HoverEvent.showText(Component.text("Klicke um einen 1v1 Kamp im Modi Only Sword zu akzeptieren")))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p ~ 120 ~"));

        Component axeMsg = Component.text("[Axt-Pvp AKZEPTIEREN]").hoverEvent(HoverEvent.showText(Component.text("Klicke um einen 1v1 Kamp im Modi Axt-Pvp zu akzeptieren")))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p ~ 130 ~"));

*/
