package me.eths.opensg.listener;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.listener.player.PlayerJoinListener;
import me.eths.opensg.listener.player.PlayerQuitListener;
import me.eths.opensg.listener.player.TestListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ListenerHandler {

    public ListenerHandler(SGPlugin plugin) {
        Arrays.asList(
                new PlayerJoinListener(plugin),
                new PlayerQuitListener(plugin),
                new TestListener(plugin)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }

}
