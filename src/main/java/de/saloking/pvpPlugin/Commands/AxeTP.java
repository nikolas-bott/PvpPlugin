package de.saloking.pvpPlugin.Commands;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AxeTP implements CommandExecutor {
    private final PvpPlugin plugin;

    public AxeTP(PvpPlugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String kommand = (String) this.plugin.getConfig().get("AXE-TP");
        commandSender.sendMessage(kommand);
        return true;
    }
}
