package de.saloking.pvpPlugin.Events;

import de.saloking.pvpPlugin.PvpPlugin;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerInteractPlayer implements Listener {
    private HashMap<UUID, UUID> mapBothPlayer = new HashMap<>();
    private HashMap<UUID, String> mapPlayer1 = new HashMap<>();

    private HashMap<Player, Player> inMatch = new HashMap<>();
    private HashMap<Player, Player> RinMatch = new HashMap<>();
    private HashMap<String, Player> inArena = new HashMap<>();

    private List<Integer> uhcList = new ArrayList<>();
    private List<Integer> axeList = new ArrayList<>();
    private List<Integer> osList = new ArrayList<>();
    // Es gibtg verschiedene Arnenen bspw. "UHC 1" oder "UHC 2" usw...
    private final PvpPlugin plugin;

    public PlayerInteractPlayer(PvpPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PrePlayerAttackEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getAttacked() instanceof Player player) {

            //UHC---
            if (p.getItemInHand().getType().equals(Material.GOLDEN_APPLE)) {
                mapPut(player, p, "UHC");
                if (isThere(player, p)) {
                    p.sendMessage("Du hast die Kampf im Modi UHC gegen " + player.getDisplayName() + " Akzeptiert!");
                    player.sendMessage(p.getDisplayName() + " hat den Kampf im Modi UHC akzeptiert!");

                    if (uhcList.size() < 10) {
                        startPVP(p, player, p.getWorld(), "UHC-TP", uhcList.size());
                        uhcList.add(uhcList.size() + 1);
                    } else {
                        p.sendMessage(ChatColor.RED + "Du konntest leider nicht in die Arena teleportiert werden, da momentan alle Arenen besspielt werden. (warte einige sekunden/minuten)");
                        player.sendMessage(ChatColor.RED + "Du konntest leider nicht in die Arena teleportiert werden, da momentan alle Arenen besspielt werden. (warte einige sekunden/minuten");
                    }

                    return;
                }
                player.sendMessage(ChatColor.BOLD + "" + p.getDisplayName() + "" + ChatColor.GOLD + " hat dich zu einem UHC Battle herausgefordert.");

                //Axe-PVP---
            } else if (p.getItemInHand().getType().equals(Material.IRON_AXE)) {
                mapPut(player, p, "AXE");
                if (isThere(player, p)) {
                    p.sendMessage("Du hast den Kampf im Modi Axt-Pvp gegen " + player.getDisplayName() + " Akzeptiert!");
                    player.sendMessage(p.getDisplayName() + " hat den Kampf im Modi Axt-Pvp akzeptiert!");

                    startPVP(p, player, p.getWorld(), "AXE-TP", axeList.size());
                    axeList.add(axeList.size() + 1);

                    return;
                }
                player.sendMessage(ChatColor.BOLD + "" + p.getDisplayName() + "" + ChatColor.WHITE + " hat dich zu einem Axt-Pvp Battle herausgefordert.");

                //Only-Sword---
            } else if (p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
                mapPut(player, p, "OS");
                if (isThere(player, p)) {
                    p.sendMessage("Du hast die Kampf im Modi Only Sword gegen " + player.getDisplayName() + " Akzeptiert!");
                    player.sendMessage(p.getDisplayName() + " hat den Kampf im Modi Only Sword akzeptiert!");

                    startPVP(p, player, p.getWorld(), "OS-TP", osList.size());
                    osList.add(osList.size() + 1);

                    return;
                }
                player.sendMessage(ChatColor.BOLD + "" + p.getDisplayName() + "" + ChatColor.BLUE + " hat dich zu einem Only Sword Battle herausgefordert.");
            }
        }
    }

    //Methoden---

    //Spieler Daten in die HashMap eintragen
    public void mapPut(Player player, Player p, String modi) {
        mapBothPlayer.put(player.getUniqueId(), p.getUniqueId());
        mapPlayer1.put(p.getUniqueId(), modi);
    }

    //Schauen ob sich zwei Spieler im gleichen Modi angefragt haben
    public boolean isThere(Player player, Player p) {
        if (mapBothPlayer.containsKey(player.getUniqueId()) && mapBothPlayer.containsKey(p.getUniqueId())) {
            if (mapPlayer1.get(player.getUniqueId()).equals(mapPlayer1.get(p.getUniqueId()))) {
                return true;
            }
        }
        return false;
    }

    //Teleportiert die Spieler zu der Arena
    public void startPVP(Player p1, Player p2, World world, String configDesti, int numberInList) {
        int z = plugin.getConfig().getInt(configDesti + ".Z");
        int zNew = z + (numberInList * 150);

        Location location = new Location
                (world, plugin.getConfig().getInt(configDesti + ".X"), plugin.getConfig().getInt(configDesti + ".Y"), zNew);
        p1.teleport(location);
        //p2.teleport(location);
        p1.sendTitle("...", "");
        p2.sendTitle("...", "");

        inMatch.put(p1, p2);
        RinMatch.put(p2, p1);
        inArena.put(configDesti + " " + numberInList, p1);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        if (inMatch.containsKey(e.getPlayer()) || inMatch.containsValue(e.getPlayer())) {
            Player killed = e.getPlayer();
            Location location = new Location
                    (e.getPlayer().getWorld(), plugin.getConfig().getInt("Lobby.X"),
                            plugin.getConfig().getInt("Lobby.Y"), plugin.getConfig().getInt("Lobby.Z"));

            if (inMatch.containsKey(e.getPlayer())) {
                Player killer = inMatch.get(e.getPlayer());

                killed.sendMessage(ChatColor.RED + "Du hast gegen " + killer.getDisplayName() + " verloren! " + killer.getDisplayName() + " hatte noch " + killer.getHealth() + "/20 Leben.");
                killer.sendMessage(ChatColor.GREEN + "Du hast gegen" + killed.getDisplayName() + " gewonnen!");

                killer.teleport(location);
            } else {
                Player killer = RinMatch.get(e.getPlayer());

                killed.sendMessage(ChatColor.RED + "Du hast gegen " + killer.getDisplayName() + " verloren! " + killer.getDisplayName() + " hatte noch " + killer.getHealth() + "/20 Leben.");
                killer.sendMessage(ChatColor.GREEN + "Du hast gegen" + killed.getDisplayName() + " gewonnen!");

                killer.teleport(location);
                killed.teleport(location);

                String temp = null;
                String[] tempAray;
                String mode = null;
                int number = 0;

                for (String key : inArena.keySet()) {
                    if (inArena.containsValue(killer) || inArena.containsValue(killed)) {
                        temp = key;
                        tempAray = temp.split(" ");

                        number = Integer.valueOf(tempAray[1]);
                        mode = tempAray[0];
                    }
                }

                if (mode.contains("UHC")) {
                    inArena.remove("UHC-TP " + number);
                } else if (mode.contains("OS")) {
                    inArena.remove("OS-TP " + number);
                } else if (mode.contains("AXE")) {
                    inArena.remove("AXE-TP " + number);
                }

            }

        }

    }
}

