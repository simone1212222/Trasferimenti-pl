package com.dev.transferPlugin.listeners;

import com.dev.transferPlugin.TransferPlugin;
import com.dev.transferPlugin.database.DatabaseManager;
import com.dev.transferPlugin.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class TrasferisciGUI implements Listener {

    private final TransferPlugin plugin;
    private final DatabaseManager databaseManager;

    public TrasferisciGUI(TransferPlugin plugin, DatabaseManager databaseManager) {
        this.plugin = plugin;
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if the clicked inventory is the transfer GUI
        if (event.getView().getTitle().equals("Trasferisci Oggetti")) {
            // If the player clicks the top inventory
            if (event.getClickedInventory() == event.getView().getTopInventory()) {
                int slot = event.getRawSlot();
                // Block interaction with non-allowed slots
                if (slot < 11 || slot > 15) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Check if the closed inventory is the transfer GUI
        if (event.getView().getTitle().equals("Trasferisci Oggetti")) {
            Player player = (Player) event.getPlayer();
            Inventory gui = event.getInventory();

            // Check if there are items in the central slots
            boolean hasItems = false;
            for (int slot : new int[]{12, 13, 14, 15, 16}) {
                if (gui.getItem(slot) != null) {
                    hasItems = true;
                    break;
                }
            }

            if (hasItems) {
                // Set the player as pending confirmation
                plugin.getConfirmationManager().setPendingConfirmation(player, gui);

                // Send a confirmation message
                player.sendMessage(MessageUtils.getConfirmationMessage());
            } else {
                // If there are no items, simply close the GUI
                player.sendMessage(MessageUtils.getGuiClosed());
            }
        }
    }
}