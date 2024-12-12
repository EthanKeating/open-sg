package me.eths.opensg.listener;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.listener.player.PlayerJoinListener;
import me.eths.opensg.listener.player.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ListenerHandler {

    private final SGPlugin plugin;
    private final ArrayList<Listener> listeners;

    public ListenerHandler(SGPlugin plugin) {
        this.plugin = plugin;

        listeners = new ArrayList<>(Arrays.asList(
                new PlayerJoinListener(plugin),
                new PlayerQuitListener(plugin)
        ));

        listeners.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

    }

}
