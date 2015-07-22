package com.shdwlf.PotionCommands.commands;

import com.shdwlf.PotionCommands.Lang;
import com.shdwlf.PotionCommands.PotionCommands;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class MilkCommand extends BaseCommand {

    public MilkCommand(PotionCommands plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("milk")) {
            if(sender instanceof Player) {
                Player player;
                if (args.length == 0) {
                    player = (Player) sender;
                } else {
                    player = Bukkit.getPlayerExact(args[0]);
                }

                if(player == null) {
                    sender.sendMessage(Lang.TITLE.toString() + Lang.OFFLINE_PLAYER.toString());
                    return true;
                }else {
                    if(sender.hasPermission("potioncommands.milk")) {
                        int cooldown = plugin.getCooldownManager().checkCooldown(player, "milk", plugin.getConfig().getDouble("milk.cooldown", 0));
                        if(cooldown > 0 && !player.hasPermission("potioncommands.milk.bypass")) {
                            player.sendMessage(Lang.TITLE.toString() + Lang.COOLDOWN.toString().replace("%t%", cooldown / 20 + ""));
                            return true;
                        }else {
                            plugin.getCooldownManager().applyCooldown(player, "milk");
                        }
                        if(!player.getName().equalsIgnoreCase(sender.getName()) && !sender.hasPermission("potioncommands.milk.other")) {
                            sender.sendMessage(Lang.TITLE.toString() + Lang.PERMISSION.toString());
                            return true;
                        }else {
                            for(PotionEffect effect : player.getActivePotionEffects())
                                player.removePotionEffect(effect.getType());
                            sender.sendMessage(Lang.TITLE.toString() + Lang.MILK_SUCCESS.toString().replace("%p%", player.getName()));
                            return true;
                        }
                    }else {
                        sender.sendMessage(Lang.TITLE.toString() + Lang.PERMISSION.toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
