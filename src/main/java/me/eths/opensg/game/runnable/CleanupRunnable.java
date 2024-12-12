package me.eths.opensg.game.runnable;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.game.settings.GameSettings;
import me.eths.opensg.game.state.GameState;
import me.eths.opensg.game.timer.GameTimer;
import org.bukkit.scheduler.BukkitRunnable;

public class CleanupRunnable extends BukkitRunnable {

    private final SGPlugin plugin;
    private final GameHandler gameHandler;
    private final GameTimer gameTimer;
    private final GameSettings gameSettings;

    public CleanupRunnable(SGPlugin plugin) {
        this.plugin = plugin;
        this.gameHandler = plugin.getGameHandler();
        this.gameTimer = gameHandler.getGameTimer();
        this.gameSettings = gameHandler.getGameSettings();

        gameTimer.setTime(gameSettings.getDeathmatchTime());
        //Initialize all data
    }

    public void run() {
        int currentTime = gameTimer.decrement();

        //Switch to pregame
        if (currentTime == 0) {
            cancel();
        }
    }

    public void cancel() {
        super.cancel();
        gameHandler.setGameState(GameState.RESTARTING);
        //Handle resetting server here
        //Maybe have a method in GameHandler that will reinit everything?
    }

}