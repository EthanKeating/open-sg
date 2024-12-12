package me.eths.opensg.listener.player;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.profile.Profile;
import me.eths.opensg.sidebar.SidebarImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class PlayerJoinListener implements Listener {

    private final SGPlugin plugin;
    public PlayerJoinListener(SGPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileHandler().createProfile(player);

        event.setJoinMessage(null);

        profile.getSidebar().setTitle("&aLobby &c2:00");
        profile.getSidebar().setLines(
                Arrays.asList(
                        "&6&l» Time",
                        "&f10 Jun 2014",
                        "&f07:05 PM EDT",
                        "",
                        "&6&l» Server",
                        "&3NA&8: &f200",
                        "",
                        "&6&l» Players",
                        "&fPlaying: 2"
                )
        );

    }

}
