package com.dev.transferPlugin.commands;

import com.dev.transferPlugin.TransferPlugin;
import org.bukkit.command.CommandExecutor;

public class CommandManager {

    private final TransferPlugin plugin;

    public CommandManager(final TransferPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        // Register commands
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        if (plugin.getCommand(commandName) != null) {
            plugin.getCommand(commandName).setExecutor(executor);
        } else {
            plugin.getLogger().warning("Command " + commandName + " not found in plugin.yml!");
        }
    }
}