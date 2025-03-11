package com.dev.transferPlugin.utils;

import com.dev.transferPlugin.TransferPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ConfirmationManager {

    private final TransferPlugin plugin;
    private final HashMap<UUID, Inventory> pendingConfirmations = new HashMap<>();

    public ConfirmationManager(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Sets a pending confirmation for the player.
     * Starts a timer to automatically cancel the confirmation if the player doesn't confirm in time.
     *
     * @param player The player who needs to confirm.
     * @param gui    The inventory to be confirmed.
     */
    public void setPendingConfirmation(Player player, Inventory gui) {
        UUID playerId = player.getUniqueId();
        pendingConfirmations.put(playerId, gui);

        // Start a timer for the confirmation
        int confirmationTime = plugin.getConfig().getInt("transfer.confirmation-time", 30); // Default time: 30 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isPendingConfirmation(playerId)) {
                    // Cancel the operation if the time expires
                    player.sendMessage(MessageUtils.getTransferCancelled());
                    removePendingConfirmation(playerId);
                }
            }
        }.runTaskLater(plugin, confirmationTime * 20L); // Convert seconds to ticks (20 ticks = 1 second)
    }

    /**
     * Checks if a player has a pending confirmation.
     *
     * @param playerId The UUID of the player.
     * @return True if the player has a pending confirmation, otherwise false.
     */
    public boolean isPendingConfirmation(UUID playerId) {
        return pendingConfirmations.containsKey(playerId);
    }

    /**
     * Retrieves and removes the pending confirmation for a player.
     *
     * @param playerId The UUID of the player.
     * @return The inventory associated with the pending confirmation.
     */
    public Inventory getPendingConfirmation(UUID playerId) {
        return pendingConfirmations.remove(playerId); // Remove and return the inventory
    }

    /**
     * Removes a pending confirmation for a player.
     *
     * @param playerId The UUID of the player.
     */
    public void removePendingConfirmation(UUID playerId) {
        pendingConfirmations.remove(playerId);
    }
}