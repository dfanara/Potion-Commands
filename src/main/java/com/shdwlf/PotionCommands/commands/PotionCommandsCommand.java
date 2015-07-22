package com.shdwlf.PotionCommands.commands;

import com.shdwlf.PotionCommands.Lang;
import com.shdwlf.PotionCommands.PotionCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class PotionCommandsCommand extends BaseCommand {

    public PotionCommandsCommand(PotionCommands plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("potioncommands")) {
            if(args.length == 0) {
                sender.sendMessage(Lang.TITLE.toString() + ChatColor.GRAY + "Potion Commands" + ChatColor.AQUA + " v"
                        + plugin.getDescription().getVersion() + ChatColor.GRAY + " by" + ChatColor.GREEN + " Shadowwolf97");
                if(sender.hasPermission("potioncommands.reload"))
                    sender.sendMessage(Lang.TITLE.toString() + ChatColor.RED + "Usage: /potioncommands reload");
            }else if(args[0].equalsIgnoreCase("reload")) {
                if(sender.hasPermission("potioncommands.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(Lang.TITLE.toString() + Lang.RELOADED.toString());
                }else {
                    sender.sendMessage(Lang.TITLE.toString() + Lang.PERMISSION.toString());
                }
            }else if(args[0].equalsIgnoreCase("help")) {
                if(sender.hasPermission("potioncommands.reload"))
                    sender.sendMessage(Lang.TITLE.toString() + ChatColor.RED + "Usage: /potioncommands reload");
                else
                    sender.sendMessage(Lang.TITLE.toString() + ChatColor.RED + "Potion Commands has no default commands.");
            }
            return true;
        }
        return false;
    }

}
