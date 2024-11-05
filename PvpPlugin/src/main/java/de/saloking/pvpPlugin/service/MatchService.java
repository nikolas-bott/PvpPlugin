package de.saloking.pvpPlugin.service;

import de.saloking.pvpPlugin.PvpPlugin;
import de.saloking.pvpPlugin.match.MatchMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class MatchService {

    private final PvpPlugin plugin;
    private final HashMap<UUID, UUID> playerMatchRequests = new HashMap<>();
    private final HashMap<UUID, MatchMode> playerMatchModes = new HashMap<>();
    private final Map<Player, Player> activeMatches = new HashMap<>();
    private final Map<String, Player> activeArenas = new HashMap<>();
    private final EnumMap<MatchMode, List<Integer>> arenaLists = new EnumMap<>(MatchMode.class);

    public MatchService(PvpPlugin plugin) {
        this.plugin = plugin;

        for (MatchMode mode : MatchMode.values()) {
            arenaLists.put(mode, new ArrayList<>());
        }
    }

    public MatchMode getMatchModeFromItem(Material material) {
        return switch (material) {
            case GOLDEN_APPLE -> MatchMode.UHC;
            case IRON_AXE -> MatchMode.AXE;
            case DIAMOND_SWORD -> MatchMode.ONLY_SWORD;
            default -> null;
        };
    }

    public void initiateMatch(Player challenger, Player target, MatchMode mode) {
        List<Integer> arenaList = arenaLists.get(mode);
        String arenaKey = mode + "-TP";

        registerMatchRequest(challenger, target, mode);

        if (!isMutualMatchRequest(challenger, target)) {
            plugin.getMatchNotifyService().notifyChallenge(target, challenger, mode);
            return;
        }

        plugin.getMatchNotifyService().notifyAcceptance(challenger, target, mode);

        if (arenaList.size() >= 10) {
            plugin.getMatchNotifyService().notifyArenaFull(challenger, target);
        }

        startMatch(challenger, target, challenger.getWorld(), arenaKey, arenaList.size());
        arenaList.add(arenaList.size() + 1);
    }

    private void registerMatchRequest(Player challenger, Player target, MatchMode mode) {
        playerMatchRequests.put(target.getUniqueId(), challenger.getUniqueId());
        playerMatchModes.put(challenger.getUniqueId(), mode);
    }

    private boolean isMutualMatchRequest(Player challenger, Player target) {
        UUID targetId = target.getUniqueId();
        UUID challengerId = challenger.getUniqueId();

        return playerMatchRequests.containsKey(targetId) && playerMatchRequests.containsKey(challengerId) &&
                playerMatchModes.get(targetId) == playerMatchModes.get(challengerId);
    }

    private void startMatch(Player challenger, Player target, World world, String configDest, int arenaNumber) {
        int z = plugin.getConfig().getInt(configDest + ".Z") + (arenaNumber * 150);
        Location arenaLocation = new Location(world, plugin.getConfig().getInt(configDest + ".X"),
                plugin.getConfig().getInt(configDest + ".Y"), z);

        challenger.teleport(arenaLocation);
        challenger.sendTitle("Prepare for battle", "");
        target.sendTitle("Prepare for battle", "");

        activeMatches.put(challenger, target);
        activeArenas.put(configDest + " " + arenaNumber, challenger);
    }

    public boolean isPlayerInMatch(Player player) {
        return activeMatches.containsKey(player) || activeMatches.containsValue(player);
    }

    public void handleMatchEnd(Player deceased) {
        Location lobbyLocation = new Location(deceased.getWorld(),
                plugin.getConfig().getInt("Lobby.X"),
                plugin.getConfig().getInt("Lobby.Y"),
                plugin.getConfig().getInt("Lobby.Z"));

        Player winner = activeMatches.getOrDefault(deceased, null);
        if (winner == null) {
            for (Map.Entry<Player, Player> entry : activeMatches.entrySet()) {
                if (entry.getValue().equals(deceased)) {
                    winner = entry.getKey();
                    break;
                }
            }
        }

        if (winner != null) {
            plugin.getMatchNotifyService().notifyMatchOutcome(winner, deceased);
            teleportPlayersToLobby(winner, deceased, lobbyLocation);
            clearArena(winner, deceased);
        }
    }

    private void teleportPlayersToLobby(Player winner, Player loser, Location lobbyLocation) {
        winner.teleport(lobbyLocation);
        loser.teleport(lobbyLocation);
    }

    private void clearArena(Player winner, Player loser) {
        String arenaKey = null;
        MatchMode mode = null;
        int arenaNumber = 0;

        for (String key : activeArenas.keySet()) {
            if (activeArenas.containsValue(winner) || activeArenas.containsValue(loser)) {
                arenaKey = key;
                String[] keyParts = key.split(" ");
                mode = MatchMode.valueOf(keyParts[0]);
                arenaNumber = Integer.parseInt(keyParts[1]);
                break;
            }
        }

        if (arenaKey != null) {
            activeArenas.remove(arenaKey);
            arenaLists.get(mode).remove((Integer) arenaNumber);
            activeMatches.remove(winner);
            activeMatches.remove(loser);
        }
    }
}
