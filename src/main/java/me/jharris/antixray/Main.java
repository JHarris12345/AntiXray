package me.jharris.antixray;

import jdk.nashorn.internal.ir.Block;
import me.jharris.antixray.Commands.Alerts;
import me.jharris.antixray.Commands.Troll;
import me.jharris.antixray.Events.BlockMineEvent;
import me.jharris.antixray.Events.CoalDropEvent;
import me.jharris.antixray.Events.PlayerLogout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The plugin has been enabled!");
        getServer().getPluginManager().registerEvents(new BlockMineEvent(this),this);
        getServer().getPluginManager().registerEvents(new CoalDropEvent(this),this);
        getServer().getPluginManager().registerEvents(new PlayerLogout(this),this);
        getServer().getPluginCommand("axalerts").setExecutor(new Alerts(this));
        getServer().getPluginCommand("axtroll").setExecutor(new Troll(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equals("axreload")) {
            if (args.length > 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if(player.hasPermission("antixray.reload")) {
                        player.sendMessage(ChatColor.RED + "Did you mean /axreload?");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                    }
                } else {
                    System.out.println(ChatColor.WHITE + "[AntiXray] " + ChatColor.RED + "Did you mean `axreload`?");
                }

            } else {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if(player.hasPermission("antixray.reload")) {
                        reloadConfig();
                        BlockMineEvent.rareores.clear();
                        for (String i : getConfig().getStringList("Ores")) BlockMineEvent.rareores.add(i);
                        BlockMineEvent.worldblacklist.clear();
                        for (String w : getConfig().getStringList("World-Blacklist"))BlockMineEvent.worldblacklist.add(w);
                        player.sendMessage(ChatColor.GREEN + "The config has been reloaded!");
                        System.out.println(ChatColor.WHITE + "[AntiXray] " + ChatColor.GREEN + "The config has been reloaded!");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                    }

                } else {
                    reloadConfig();
                    BlockMineEvent.rareores.clear();
                    for (String i : getConfig().getStringList("Ores")) BlockMineEvent.rareores.add(i);
                    BlockMineEvent.worldblacklist.clear();
                    for (String w : getConfig().getStringList("World-Blacklist"))BlockMineEvent.worldblacklist.add(w);
                    System.out.println(ChatColor.WHITE + "[AntiXray] " + ChatColor.GREEN + "The config has been reloaded!");
                }
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("The plugin has been disabled!");
    }
}
