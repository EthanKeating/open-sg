package me.eths.opensg.profile;

import lombok.Getter;
import me.eths.opensg.SGPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.WeakHashMap;

public class ProfileHandler {

    @Getter private final HashMap<UUID, Profile> profileMap = new HashMap<>();
    private final SGPlugin plugin;

    public ProfileHandler(SGPlugin plugin) {
        this.plugin = plugin;
    }

    public Profile getProfile(UUID uuid) {
        return profileMap.get(uuid);
    }

    public Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

    public Profile createProfile(Player player) {
        Profile profile = new Profile(player, plugin);
        profileMap.put(player.getUniqueId(), profile);
        return profile;
    }

    public Profile deleteProfile(Player player) {
        if (!profileMap.containsKey(player.getUniqueId())) return null;

        Profile profile = profileMap.get(player.getUniqueId());
        profileMap.remove(player.getUniqueId());

        return profile;
    }

}
