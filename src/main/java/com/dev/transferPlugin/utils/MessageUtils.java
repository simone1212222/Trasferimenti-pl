package com.dev.transferPlugin.utils;

import com.dev.transferPlugin.TransferPlugin;
import org.bukkit.ChatColor;

public class MessageUtils {

    private static TransferPlugin plugin;

    // Initialize
    public static void setPlugin(TransferPlugin plugin) {
        MessageUtils.plugin = plugin;
    }

    /**
     * Gets the "command player only" message.
     *
     * @return The formatted message.
     */
    public static String getCommandPlayerOnly() {
        return plugin.getConfig().getString("messages.command-player-only", "&cSolo i player possono fare questo comando.");
    }

    /**
     * Gets the "GUI opened" message.
     *
     * @return The formatted message.
     */
    public static String getGuiOpened() {
        return plugin.getConfig().getString("messages.gui-opened", "&aHai aperto il sistema di transferimento oggetti");
    }

    /**
     * Gets the "GUI closed" message.
     *
     * @return The formatted message.
     */
    public static String getGuiClosed() {
        return plugin.getConfig().getString("messages.gui-closed", "&aHai chiuso la GUI. I tuoi oggetti si sono salvati.");
    }

    /**
     * Gets the "command disabled" message.
     *
     * @return The formatted message.
     */
    public static String getCommandDisabled() {
        return plugin.getConfig().getString("messages.command-disabled", "&cPuoi solo usare questo comando 1 volta");
    }

    /**
     * Gets the "confirmation required" message.
     *
     * @return The formatted message.
     */
    public static String getConfirmationMessage() {
        return plugin.getConfig().getString("messages.confirmation-required", "&eSei sicuro di trasferire gli oggetti? Usa &a/transferconfirm &per confermare");
    }

    /**
     * Gets the "transfer confirmed" message.
     *
     * @return The formatted message.
     */
    public static String getTransferConfirmed() {
        return plugin.getConfig().getString("messages.transfer-confirmed", "&aTransfer confermato");
    }

    /**
     * Gets the "transfer cancelled" message.
     *
     * @return The formatted message.
     */
    public static String getTransferCancelled() {
        return plugin.getConfig().getString("messages.transfer-cancelled", "&cTransfer cancellato");
    }

    /**
     * Gets the "no pending confirmation" message.
     *
     * @return The formatted message.
     */
    public static String getNoPendingConfirmation() {
        return plugin.getConfig().getString("messages.no-pending-confirmation", "&cUsa /trasferisci prima");
    }

    /**
     * Translates color codes in a message.
     *
     * @param message The message to translate.
     * @return The translated message.
     */
    public static String translateColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}