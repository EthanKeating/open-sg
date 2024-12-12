package me.eths.opensg.listener.player;

import me.eths.opensg.SGPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    private final SGPlugin plugin;
    public PlayerJoinListener(SGPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        //Handle player join
    }

}
