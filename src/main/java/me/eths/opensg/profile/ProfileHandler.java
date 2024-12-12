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
        Profile profile = new Profile(player);
        profileMap.put(player.getUniqueId(), profile);
        return profile;
    }

    public void deleteProfile(Player player) {
        if (!profileMap.containsKey(player.getUniqueId())) return;
        profileMap.remove(player.getUniqueId());
    }

}
