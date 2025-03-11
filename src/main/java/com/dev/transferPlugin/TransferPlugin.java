package com.dev.transferPlugin;

import com.dev.transferPlugin.commands.CommandManager;
import com.dev.transferPlugin.database.DatabaseManager;
import com.dev.transferPlugin.listeners.TrasferisciGUI;
import com.dev.transferPlugin.utils.ConfirmationManager;
import com.dev.transferPlugin.utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class TransferPlugin extends JavaPlugin {

    private DatabaseManager databaseManager;

    private ConfirmationManager confirmationManager;

    @Override
    public void onEnable() {
        // Load & Reload config.yml
        saveDefaultConfig();
        reloadConfig();

        // Initialize MessageUtils
        MessageUtils.setPlugin(this);

        // Initialize DatabaseConfig
        databaseManager = new DatabaseManager(this);

        try {
            databaseManager.connect();
            getLogger().info("Connected to MySQL database.");
        } catch (SQLException e) {
            getLogger().severe("Error during MySQL connection " + e.getMessage());
            e.printStackTrace();
        }

        // Initialize ConfirmationManager
        this.confirmationManager = new ConfirmationManager(this);

        // Register commands
        new CommandManager(this).registerCommands();

        // Register listener
        getServer().getPluginManager().registerEvents(new TrasferisciGUI(this, databaseManager), this);

        getLogger().info("TransferPlugin enabled!");
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    public ConfirmationManager getConfirmationManager() {
        return confirmationManager;
    }
}