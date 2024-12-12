package me.eths.opensg;

import com.github.retrooper.packetevents.PacketEvents;
import me.eths.opensg.listener.ListenerHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class SGPlugin extends JavaPlugin {

    private ListenerHandler listenerHandler;

    public void onEnable() {
        listenerHandler = new ListenerHandler(this);
    }

    public void onDisable() {

    }

}
