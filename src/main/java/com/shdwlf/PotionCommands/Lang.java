package com.shdwlf.PotionCommands;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.potion.PotionEffectType;

public enum Lang {
    TITLE("title", "&7[&4PotionCommands&7] &r"),
    RELOADED("reloaded", "Configuration reloaded."),
    PERMISSION("permission", "&4You do not have permission."),
    OFFLINE_PLAYER("offline-player", "&4That player is not online."),
    MILK_SUCCESS("milk-success", "&aRemoved potion effects from %p%"),
    COOLDOWN("cooldown", "&cYou must wait %t% more seconds before using that command.");

    private static YamlConfiguration LANG;
    private String path;
    private String def;

    /**
     * Lang enum constructor.
     *
     * @param path  The string path.
     * @param start The default string.
     */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }



    /**
     * Set the {@code YamlConfiguration} to use.
     *
     * @param config The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        if (this == TITLE)
            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    /**
     * Get the default value of the path.
     *
     * @return The default value of the path.
     */
    public String getDefault() {
        return this.def;
    }

    /**
     * Get the path to the string.
     *
     * @return The path to the string.
     */
    public String getPath() {
        return this.path;
    }
}