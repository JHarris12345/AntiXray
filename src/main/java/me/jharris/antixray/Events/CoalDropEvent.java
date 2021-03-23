package me.jharris.antixray.Events;

import me.jharris.antixray.Commands.Troll;
import me.jharris.antixray.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CoalDropEvent implements Listener {

    Main plugin;

    public CoalDropEvent(Main plugin) {
        this.plugin = plugin;

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        String player = e.getPlayer().getName();
        Material item = Material.matchMaterial(plugin.getConfig().getString("TrollCommand.Drop"));

        if (plugin.getConfig().getBoolean("TrollCommand.Enabled")) {

            if (BlockMineEvent.rareores.contains(block.getType().name())) {
                if (Troll.trollist.contains(player)) {
                    e.setDropItems(false);
                    ItemStack droppeditem = new ItemStack(item, 1);
                    block.getWorld().dropItemNaturally(block.getLocation(), droppeditem);

                } else return;
            }
        } else return;
    }
}
