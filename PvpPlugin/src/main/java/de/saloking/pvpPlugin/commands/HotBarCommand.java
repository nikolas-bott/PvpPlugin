package de.saloking.pvpPlugin.commands;

import de.saloking.pvpPlugin.PvpPlugin;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class HotBarCommand {

    public HotBarCommand(PvpPlugin plugin) {
        PluginCommand pluginCommand = plugin.getCommand("openInv");

        if (pluginCommand == null) {
            plugin.getLogger().warning("Could not find command 'openInv'");
            return;
        }

        pluginCommand.setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player player))
                return true;

            plugin.getPlayerHotbar().putHotbarItems(player);

            return true;
        });
    }
}
