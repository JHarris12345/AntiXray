package me.jharris.antixray.Commands;

import me.jharris.antixray.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Alerts implements CommandExecutor {

    public static ArrayList<String> alertslist = new ArrayList<>();
    Main plugin;

    public Alerts(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("xray.alerts")) {
                if (alertslist.contains(player.getName())) {
                    alertslist.remove(player.getName());
                    player.sendMessage(ChatColor.GREEN + "You have enabled xray alerts");

                } else {
                    alertslist.add(player.getName());
                    player.sendMessage(ChatColor.RED + "You have disabled xray alerts");
                }

            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
            }


        } else {
            System.out.println("You must be a player to do this!");
        }
        return true;
    }
}
