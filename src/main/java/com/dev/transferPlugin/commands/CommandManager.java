package com.dev.transferPlugin.commands;

import com.dev.transferPlugin.TransferPlugin;
import org.bukkit.command.CommandExecutor;

public class CommandManager {

    private final TransferPlugin plugin;

    public CommandManager(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        plugin.getCommand("trasferisci").setExecutor(new TrasferisciCommand(plugin));
        //plugin.getCommand("resettrasferisci").setExecutor(new ResetTrasferisciCommand(plugin));
        plugin.getCommand("transferconfirm").setExecutor(new TransferConfirmCommand(plugin));
    }
}