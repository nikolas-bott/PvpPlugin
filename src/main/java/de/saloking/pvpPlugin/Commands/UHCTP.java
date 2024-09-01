package de.saloking.pvpPlugin.Commands;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UHCTP implements CommandExecutor {
    private final PvpPlugin plugin;

    public UHCTP(PvpPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String kommand = (String) this.plugin.getConfig().get("UHC-TP");
        commandSender.sendMessage(kommand);
        return true;
    }
}
