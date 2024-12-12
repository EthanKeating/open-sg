package me.eths.opensg.listener.player;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.sidebar.SidebarImpl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class PlayerJoinListener implements Listener {

    private final SGPlugin plugin;
    public PlayerJoinListener(SGPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        SidebarImpl sidebar = new SidebarImpl(event.getPlayer().getUniqueId());

        sidebar.setTitle("Testing");
        sidebar.setLines(Arrays.asList(
                "Testing",
                "TResting2",
                "Testing3"
        ));
    }

}
