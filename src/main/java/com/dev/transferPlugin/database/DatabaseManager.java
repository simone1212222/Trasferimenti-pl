package com.dev.transferPlugin.database;

import com.dev.transferPlugin.TransferPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {

    private final TransferPlugin plugin;
    private Connection connection;

    public DatabaseManager(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://" + plugin.getConfig().getString("database.host") + ":" +
                plugin.getConfig().getInt("database.port") + "/" +
                plugin.getConfig().getString("database.name") +
                "?useSSL=false&allowPublicKeyRetrieval=true";
        connection = DriverManager.getConnection(url,
                plugin.getConfig().getString("database.username"),
                plugin.getConfig().getString("database.password"));
    }

    public void saveInventory(UUID uuid, String serializedInventory) {
        String query = "INSERT INTO player_items (uuid, inventory) VALUES (?, ?) ON DUPLICATE KEY UPDATE inventory = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid.toString());
            statement.setString(2, serializedInventory);
            statement.setString(3, serializedInventory);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean hasUsedCommand(UUID uuid) {
        // Implementa la logica per verificare se il player ha già usato il comando
        return false; // Placeholder
    }

    public void disableCommand(UUID uuid) {
        // Implementa la logica per disabilitare il comando per il player
    }
}