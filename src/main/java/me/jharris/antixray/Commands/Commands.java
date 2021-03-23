/*package me.jharris.antixray.Commands;

import me.jharris.antixray.Events.BlockMineEvent;
import me.jharris.antixray.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    private Main plugin;

    ArrayList<String> subcommands = new ArrayList<String>();


    public Commands(Main plugin) {
        this.plugin = plugin;
        subcommands.add("reload");
        subcommands.add("alerts");
        subcommands.add("help");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            if (sender instanceof Player) {

                player.sendMessage(ChatColor.GOLD + "Possible AntiXray Commands:");
                player.sendMessage(ChatColor.GOLD + "/ax reload" + ChatColor.WHITE + ": Reloads the config.yml");
                player.sendMessage(ChatColor.GOLD + "/ax alerts" + ChatColor.WHITE + ": Toggles seeing xray alert messages");

            } else {
                System.out.println(ChatColor.WHITE + "[AntiXray] " + ChatColor.RED + "Did you mean `axreload`?");
            }
        }

        if (args.length == 1) {
            if (sender instanceof Player) {
                if (!args[0].equalsIgnoreCase(String.valueOf(subcommands))) {
                    player.sendMessage(ChatColor.RED + "Command not found. Try '/ax help'");

                } else {
                    if (args[0].equalsIgnoreCase("reload")) {
                        if(player.hasPermission("antixray.reload")) {
                            plugin.reloadConfig();
                            BlockMineEvent.rareores.clear();
                            for (String i : plugin.getConfig().getStringList("Ores")) BlockMineEvent.rareores.add(i);
                            BlockMineEvent.worldblacklist.clear();
                            for (String w : plugin.getConfig().getStringList("World-Blacklist"))BlockMineEvent.worldblacklist.add(w);
                            player.sendMessage(ChatColor.GREEN + "The config has been reloaded!");
                            System.out.println(ChatColor.WHITE + "[AntiXray] " + ChatColor.GREEN + "The config has been reloaded!");
                        } else {
                            player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                        }
                    } return true;

                    if (args[0].equalsIgnoreCase("help")) {
                        if(player.hasPermission("antixray.alerts")) {
                            player.sendMessage(ChatColor.GOLD + "Possible Console AntiXray Commands:");
                            player.sendMessage(ChatColor.GOLD + "/ax reload" + ChatColor.WHITE + ": Reloads the config.yml");
                            player.sendMessage(ChatColor.GOLD + "/ax alerts" + ChatColor.WHITE + ": Toggle viewing xray alerts");
                            player.sendMessage(ChatColor.GOLD + "/ax help" + ChatColor.WHITE + ": Shows all the possible commands");
                        } else {
                            player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                        }
                    }

                    if (args[0].equalsIgnoreCase("alerts")) {
                        if(player.hasPermission("antixray.alerts")) {
                        } else {
                            player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                        }
                    }
                }

            } else {
                if (args[0].equalsIgnoreCase(String.valueOf(subcommands))) {
                    player.sendMessage(ChatColor.GOLD + "Possible Console AntiXray Commands:");
                    player.sendMessage(ChatColor.GOLD + "/ax reload" + ChatColor.WHITE + ": Reloads the config.yml");

                } else {
                    player.sendMessage(ChatColor.RED + "Command not found. Try '/ax help'");
                }
            }

        }
        return true;
    }
}
*/