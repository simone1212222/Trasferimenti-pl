package com.dev.transferPlugin.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class DatabaseConfig {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public DatabaseConfig(JavaPlugin plugin) {

        plugin.saveDefaultConfig();
        plugin.reloadConfig();


        ConfigurationSection dbConfig = plugin.getConfig().getConfigurationSection("database");
        this.host = dbConfig.getString("host", "localhost");
        this.port = dbConfig.getInt("port", 3306);
        this.database = dbConfig.getString("name", "transfer_db");
        this.username = dbConfig.getString("username", "root");
        this.password = dbConfig.getString("password", "password");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}