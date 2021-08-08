package me.genesismiuss.cosmetics.utils;

import org.bukkit.ChatColor;

public class ChatFormat {

    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}
