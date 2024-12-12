package me.eths.opensg.profile;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Getter;
import me.eths.opensg.sidebar.SidebarImpl;
import me.eths.opensg.util.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;
    private final User user;
    private String name;

    private final SidebarImpl sidebar;

    public Profile(Player player) {
        uuid = player.getUniqueId();
        this.user = PacketEvents.getAPI().getProtocolManager().getUser(
                ((CraftPlayer) player)
                        .getHandle()
                        .playerConnection
                        .networkManager
                        .channel
        );

        sidebar = new SidebarImpl(this);
    }

}
