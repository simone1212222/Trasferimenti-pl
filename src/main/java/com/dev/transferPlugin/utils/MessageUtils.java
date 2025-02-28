package com.dev.transferPlugin.utils;

public class MessageUtils {

    public static String getCommandPlayerOnly() {
        return "§cSolo i giocatori possono eseguire questo comando.";
    }

    public static String getNoPermission() {
        return "§cNon hai il permesso per usare questo comando.";
    }

    public static String getGuiOpened() {
        return "§aHai aperto la GUI di trasferimento!";
    }

    public static String getGuiClosed() {
        return "§aHai chiuso la GUI. Gli item verranno salvati.";
    }

    public static String getCommandDisabled() {
        return "§cNon puoi più usare il comando /trasferisci.";
    }
}
