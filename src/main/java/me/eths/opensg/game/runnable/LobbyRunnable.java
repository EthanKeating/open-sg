package me.eths.opensg.game.runnable;

import me.eths.opensg.SGPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyRunnable extends BukkitRunnable {

    private final SGPlugin plugin;

    public LobbyRunnable(SGPlugin plugin) {
        this.plugin = plugin;

        //Initialize all data
    }

    public void run() {
        //Update data every second
    }

    public void cancel() {
        //Cleanup data for next runnable
    }

}
