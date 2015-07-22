package com.shdwlf.PotionCommands.commands;

import com.shdwlf.PotionCommands.PotionCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class BaseCommand implements CommandExecutor {

    protected final PotionCommands plugin;

    public BaseCommand(PotionCommands plugin) {
        this.plugin = plugin;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

}
