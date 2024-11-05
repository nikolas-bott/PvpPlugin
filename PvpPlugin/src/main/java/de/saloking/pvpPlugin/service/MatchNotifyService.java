package de.saloking.pvpPlugin.service;

import de.saloking.pvpPlugin.match.MatchMode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MatchNotifyService {

    public void notifyAcceptance(Player challenger, Player target, MatchMode mode) {
        String acceptanceMessage = "You have accepted a " + mode + " match against " + target.getDisplayName() + "!";
        challenger.sendMessage(acceptanceMessage);
        target.sendMessage(challenger.displayName() + " has accepted the " + mode + " match!");
    }

    public void notifyChallenge(Player target, Player challenger, MatchMode mode) {
        String challengeMessage = challenger.displayName() + " has challenged you to a " + mode + " match.";
        target.sendMessage(ChatColor.BOLD + challengeMessage);
    }

    public void notifyArenaFull(Player challenger, Player target) {
        String fullMessage = ChatColor.RED + "All arenas are currently occupied. Please wait a few seconds/minutes.";
        challenger.sendMessage(fullMessage);
        target.sendMessage(fullMessage);
    }

    public void notifyMatchOutcome(Player winner, Player loser) {
        loser.sendMessage(ChatColor.RED + "You lost against " + winner.displayName() + "! " +
                winner.displayName() + " had " + winner.getHealth() + "/20 health remaining.");
        winner.sendMessage(ChatColor.GREEN + "You won against " + loser.displayName() + "!");
    }
}
