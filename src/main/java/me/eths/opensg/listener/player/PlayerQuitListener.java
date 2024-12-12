package me.eths.opensg.listener.player;

import me.eths.opensg.SGPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PlayerQuitListener implements Listener {

    private final SGPlugin plugin;
    public PlayerQuitListener(SGPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        //Handle player quit
    }

}
