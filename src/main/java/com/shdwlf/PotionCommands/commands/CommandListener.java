package com.shdwlf.PotionCommands.commands;

import com.shdwlf.PotionCommands.Lang;
import com.shdwlf.PotionCommands.PotionCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandListener implements Listener {

    private final PotionCommands plugin;

    public CommandListener(PotionCommands plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String cmd = event.getMessage().split(" ")[0].substring(1).toLowerCase();
        FileConfiguration config = plugin.getConfig();
        if(config.isSet("commands." + cmd)) {
            if(!config.isSet("commands." + cmd + ".permission") || event.getPlayer().hasPermission("potioncommands." + config.getString("commands." + cmd + ".permission"))) {
                int duration = config.getInt("commands." + cmd + ".duration", 200);
                int level = config.getInt("commands." + cmd + ".amplifier", 0);
                if(config.isSet("commands." + cmd + ".cooldown")) {
                    int cooldown = plugin.getCooldownManager().checkCooldown(event.getPlayer(), cmd, config.getDouble("commands." + cmd + ".cooldown"));
                    System.out.println("cooldown on " + cmd + " for " + event.getPlayer() + " is " + cooldown);
                    if(cooldown > 0 && !event.getPlayer().hasPermission("potioncommands." + config.getString("oommands." + cmd + ".permission") + ".bypass")) {
                        event.getPlayer().sendMessage(Lang.TITLE.toString() + Lang.COOLDOWN.toString().replace("%t%", cooldown / 20 + ""));
                    }else {
                        PotionEffectType type = PotionEffectType.getByName(config.getString("commands." + cmd + ".effect", "SPEED")); //Give speed by default.
                        event.getPlayer().addPotionEffect(new PotionEffect(type, duration, level));
                        plugin.getCooldownManager().applyCooldown(event.getPlayer(), cmd);
                    }
                }else {
                    PotionEffectType type = PotionEffectType.getByName(config.getString("commands." + cmd + ".effect", "SPEED")); //Give speed by default.
                    event.getPlayer().addPotionEffect(new PotionEffect(type, duration, level));
                }

            }else {
                event.getPlayer().sendMessage(Lang.TITLE.toString() + Lang.PERMISSION.toString());
            }
            event.setCancelled(true);
        }
    }
}
