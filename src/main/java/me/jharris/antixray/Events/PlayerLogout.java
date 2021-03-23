package me.jharris.antixray.Events;

import me.jharris.antixray.Commands.Troll;
import me.jharris.antixray.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLogout implements Listener {

    Main plugin;

    public PlayerLogout(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent e) {
        String player = e.getPlayer().getName();
        Boolean logout = plugin.getConfig().getBoolean("TrollCommand.RemoveOnLogout");

        if (!logout) return;

        if (logout) {
            if (Troll.trollist.contains(player)) {
                Troll.trollist.remove(player);
            } else return;
        }

    }

}
