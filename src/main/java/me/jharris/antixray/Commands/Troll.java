package me.jharris.antixray.Commands;

import me.jharris.antixray.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Troll implements CommandExecutor {

    Main plugin;
    public static ArrayList<String> trollist = new ArrayList<>();

    public Troll(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            Player staff = (Player) sender;

            if (!staff.hasPermission("antixray.troll")) {
                staff.sendMessage(ChatColor.RED + "You don't have permission to do this!");

            } else {
                if (plugin.getConfig().getBoolean("TrollCommand.Enabled")) {
                    if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[1]);

                        if (args[0].equalsIgnoreCase("add")) {
                            if (trollist.contains(args[1])) {
                                staff.sendMessage(ChatColor.RED + "This player is already being trolled. Do you mean /axtroll remove " + args[1] + "?");

                            } else {
                                if (target instanceof Player) {
                                    staff.sendMessage(ChatColor.GREEN + target.getName() + " has been added to the troll list!");
                                    trollist.add(target.getName());

                                } else {
                                    staff.sendMessage(ChatColor.RED + args[1] + " is not a valid online player!");
                                }
                            }



                        } else if (args[0].equalsIgnoreCase("remove")) {
                            if (!trollist.contains(args[1])) {
                                staff.sendMessage(ChatColor.RED + "This player is not being trolled. Do you mean /axtroll add " + args[1] + "?");

                            } else {
                                staff.sendMessage(ChatColor.GREEN + args[1] + " has been removed from the troll list!");
                                trollist.remove(args[1]);
                            }


                        } else if (!args[0].equalsIgnoreCase("add") || (!args[0].equalsIgnoreCase("remove"))) {
                            staff.sendMessage(ChatColor.RED + "DWrong syntax! The command is '/axtroll add/remove [player]'");
                        }

                    } else {
                        staff.sendMessage(ChatColor.RED + "WWrong syntax! The command is '/axtroll add/remove [player]'");
                    }


                } else {
                    staff.sendMessage(ChatColor.RED + "This feature is disabled in config!");
                }
            }


        } else {
            System.out.println("You must be a player to do this!");
        }
        return true;
    }
}
