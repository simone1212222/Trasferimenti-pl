package com.dev.transferPlugin.commands;

import com.dev.transferPlugin.TransferPlugin;
import com.dev.transferPlugin.utils.InventoryUtils;
import com.dev.transferPlugin.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TransferConfirmCommand implements CommandExecutor {

    private final TransferPlugin plugin;

    public TransferConfirmCommand(TransferPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.getCommandPlayerOnly());
            return true;
        }

        Player player = (Player) sender;

        // Check if the player has a pending confirmation
        if (!plugin.getConfirmationManager().isPendingConfirmation(player.getUniqueId())) {
            player.sendMessage(MessageUtils.getNoPendingConfirmation());
            return true;
        }

        // Retrieve the pending inventory
        Inventory gui = plugin.getConfirmationManager().getPendingConfirmation(player.getUniqueId());

        // Serialize and save the items
        String serializedInventory = InventoryUtils.serializeSpecificSlots(gui, new int[]{12, 13, 14, 15, 16});
        plugin.getDatabaseManager().saveInventory(player.getUniqueId(), serializedInventory);

        // Disable the command for the player
        plugin.getDatabaseManager().disableCommand(player.getUniqueId());

        // Send feedback to the player
        player.sendMessage(MessageUtils.getTransferConfirmed());

        return true;
    }
}