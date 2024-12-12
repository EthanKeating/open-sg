package me.eths.opensg.game;

import lombok.RequiredArgsConstructor;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.runnable.LobbyRunnable;
import me.eths.opensg.game.state.GameState;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class GameHandler {

    private final SGPlugin plugin;
    private BukkitRunnable currentRunnable;

    public GameHandler(SGPlugin plugin) {
        this.plugin = plugin;
    }

    public void setGameState(GameState newState) {
        if (currentRunnable != null)
            currentRunnable.cancel();

        switch(newState) {
            case LOBBY:
                currentRunnable = new LobbyRunnable(plugin); break;
        }

        currentRunnable.runTaskTimer(plugin, 0L, 20L);
    }

    private void stop() {

    }

}
