package com.dev.transferPlugin.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class InventoryUtils {

    public static String serializeSpecificSlots(Inventory inventory, int[] slots) {

        HashMap<Integer, ItemStack> itemsMap = new HashMap<>();

        for (int slot : slots) {
            ItemStack item = inventory.getItem(slot);
            itemsMap.put(slot, item);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeObject(itemsMap);
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}