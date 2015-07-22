package com.shdwlf.PotionCommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CooldownManager {

    private final PotionCommands plugin;
    private YamlConfiguration cooldowns;
    private File cooldownFile;

    public CooldownManager(PotionCommands plugin) {
        this.cooldownFile = new File(plugin.getDataFolder(), "cooldowns.yml");
        this.cooldowns = new YamlConfiguration();
        try {
            if (!cooldownFile.exists()) {
                if (!cooldownFile.getParentFile().exists())
                    cooldownFile.getParentFile().mkdir();
                cooldownFile.createNewFile();
            }
            cooldowns.load(cooldownFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not load or create cooldowns.yml");
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().severe("Could not load cooldowns.yml");
            e.printStackTrace();
        }
        this.plugin = plugin;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                save();
            }
        }, 6000L, 6000L); //save every 5 minutes
    }

    public void save() {
        try {
            cooldowns.save(cooldownFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save cooldowns.yml");
            e.printStackTrace();
        }
    }

    /**
     * Check if a player is affected by a cooldown
     *
     * @param command  Command string
     * @param duration length of the cooldown to check on. Time in ticks
     * @return number of ticks until cooldown, or 0 if none.
     */
    public int checkCooldown(Player player, String command, double duration) {
        if (cooldowns.isSet(player.getUniqueId().toString() + "." + command.toLowerCase())) {
            long last = cooldowns.getLong(player.getUniqueId().toString()
                    + "." + command.toLowerCase(), 0);

            if (System.currentTimeMillis() - last < duration / 20.0 * 1000.0) {
                long time = ((long) duration / 20L * 1000L + last) - System.currentTimeMillis();
                return (int) (time / 1000.0 * 20.0);
            }
        }
        return 0;
    }

    public void applyCooldown(Player player, String command) {
        cooldowns.set(player.getUniqueId().toString() + "." + command.toLowerCase(), System.currentTimeMillis());
    }

}
