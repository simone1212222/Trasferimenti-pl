package com.dev.transferPlugin;

import com.dev.transferPlugin.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TransferPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register commands
        new CommandManager(this).registerCommands();

        // Register listeners

        getLogger().info("TransferPlugin enabled");
    }

}
