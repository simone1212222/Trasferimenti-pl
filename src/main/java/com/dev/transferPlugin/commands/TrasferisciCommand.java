package com.dev.transferPlugin.commands;

import com.dev.transferPlugin.TransferPlugin;
import com.dev.transferPlugin.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TrasferisciCommand implements CommandExecutor {

    private final TransferPlugin plugin;

    public TrasferisciCommand(TransferPlugin plugin) {
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

        // Check if the player has already used the command
        if (plugin.getDatabaseManager().hasUsedCommand(player.getUniqueId())) {
            player.sendMessage(MessageUtils.getCommandDisabled());
            return true;
        }

        // Create a GUI with 27 slots
        Inventory gui = plugin.getServer().createInventory(null, 27, "Trasferisci Oggetti");

        // Fill the borders with decorative glass panes
        ItemStack decoration = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = decoration.getItemMeta();
        meta.setDisplayName(" ");
        decoration.setItemMeta(meta);

        for (int i = 0; i < 27; i++) {
            if (i < 11 || i > 15) {
                gui.setItem(i, decoration);
            }
        }

        // Open the GUI for the player
        player.openInventory(gui);
        player.sendMessage(MessageUtils.getGuiOpened());

        // Set the player as pending confirmation
        plugin.getConfirmationManager().setPendingConfirmation(player, gui);

        return true;
    }
}