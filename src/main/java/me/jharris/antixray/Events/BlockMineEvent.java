package me.jharris.antixray.Events;

import me.jharris.antixray.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockMineEvent implements Listener {

    Main plugin;
    public static List<String> rareores = new ArrayList<String>();
    public static List<String> worldblacklist = new ArrayList<String>();


    public BlockMineEvent(Main plugin) {
        this.plugin = plugin;
        for (String i : plugin.getConfig().getStringList("Ores")) rareores.add(i);
        for (String w : plugin.getConfig().getStringList("World-Blacklist")) worldblacklist.add(w);
    }


    HashMap<String, Integer> xrayers = new HashMap<String, Integer>();


    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {

        Block block = event.getBlock();
        String player = (event.getPlayer().getName());
        Player p = event.getPlayer();
        Integer current = xrayers.get(player);
        Integer runtime = plugin.getConfig().getInt("Scanning-Time");
        boolean creative = plugin.getConfig().getBoolean("CheckCreative");
        Integer runtimemessage = runtime / 20;
        Integer threshold = plugin.getConfig().getInt("Threshold");
        boolean debug = plugin.getConfig().getBoolean("Debug");
        String world = event.getBlock().getWorld().getName();
        int xcord = event.getBlock().getLocation().getBlockX();
        int ycord = event.getBlock().getLocation().getBlockY();
        int zcord = event.getBlock().getLocation().getBlockZ();


        if (p.hasPermission("antixray.bypass")) return;
        if (worldblacklist.contains(p.getLocation().getWorld().getName())) return;
        if (!creative && p.getGameMode().equals(GameMode.CREATIVE)) return;

        if (rareores.contains(block.getType().name())) {
            if (!xrayers.containsKey(player)) {

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (xrayers.get(player) >= threshold) {
                            Integer blocknumber = xrayers.get(player);
                            xrayers.remove(player);
                            Bukkit.broadcast(ChatColor.RED + player + " might be using xray! (" + blocknumber + " rare ores mined in the last " + runtimemessage + "s at " + world + ": " + xcord + ", " + ycord + ", " + zcord + ")", "xray.alerts");
                            System.out.println("[AntiXray] " + player + " might be using xray at " + world + ": " + xcord + ", " + ycord + ", " + zcord);
                        } else {
                            if (!debug) {
                                xrayers.remove(player);
                            } else {
                                xrayers.remove(player);
                                Bukkit.broadcast(ChatColor.RED + player + " wasn't using xray in the last " + runtimemessage + "s", "xray.debug");
                            }
                        }
                    }}, runtime);

                if (!debug) {
                    xrayers.put(player, 1);
                } else {
                    xrayers.put(player, 1);
                    System.out.println(xrayers);
                }

            } else {
                if (!debug) {
                    xrayers.put(player, current + 1);
                } else {
                    xrayers.put(player, current + 1);
                    System.out.println(xrayers);
                }
            }
        } else {
            if (!debug) {
                return;
            } else {
                Bukkit.broadcast("No match " + block.getType().name(), "xray.debug");
                System.out.println(rareores);
            }
        }
        return;
    }
}

