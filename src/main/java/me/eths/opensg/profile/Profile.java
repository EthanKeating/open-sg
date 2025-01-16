package me.eths.opensg.profile;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Getter;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.design.sidebar.SidebarImpl;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final SGPlugin plugin;

    private final UUID uuid;
    private final User user;
    private String name;

    private final String timeZone;

    private final SidebarImpl sidebar;

    private final IAdapter

    public Profile(Player player, SGPlugin plugin) {
        this.plugin = plugin;
        uuid = player.getUniqueId();
        this.user = PacketEvents.getAPI().getProtocolManager().getUser(((CraftPlayer) player)
                        .getHandle()
                        .playerConnection
                        .networkManager
                        .channel
        );
        this.timeZone = plugin.getTimeHandler().retrieveTimeZone(player);

        sidebar = new SidebarImpl(this);
    }

}
