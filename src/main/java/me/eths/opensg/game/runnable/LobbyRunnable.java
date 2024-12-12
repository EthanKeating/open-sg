package me.eths.opensg.game.runnable;

import lombok.Getter;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.game.settings.GameSettings;
import me.eths.opensg.game.state.GameState;
import me.eths.opensg.game.timer.GameTimer;
import me.eths.opensg.lang.LanguageHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LobbyRunnable extends BukkitRunnable {

    private final SGPlugin plugin;
    private final GameHandler gameHandler;
    private final GameTimer gameTimer;
    private final GameSettings gameSettings;
    private final LanguageHandler languageHandler;

    public LobbyRunnable(SGPlugin plugin) {
        this.plugin = plugin;
        this.gameHandler = plugin.getGameHandler();
        this.gameTimer = gameHandler.getGameTimer();
        this.gameSettings = gameHandler.getGameSettings();
        this.languageHandler = plugin.getLanguageHandler();

        gameTimer.setTime(gameSettings.getLobbyLength());
        //Initialize all data
    }

    public void run() {
        int currentTime = gameTimer.decrement();

        List<Player> playerList = gameHandler.getPlayerList()
                .stream()
                .map(Bukkit::getPlayer)
                .collect(Collectors.toList());

        //Switch to pregame
        if (currentTime == 0) {
            if (gameSettings.getMinimumPlayers() > gameHandler.getPlayerList().size()) {
                gameTimer.reset();
                return;
            }
            gameHandler.broadcast("lobby-map-name");
            gameHandler.broadcast("lobby-map-author");
            gameHandler.broadcast("lobby-map-link");
            gameHandler.broadcast("lobby-end");

            cancel();
        }

        //Show vote messages
        if (currentTime % 30 == 0) {
            //playerList.forEach(player -> languageHandler.sendMessage(player, "lobby-count"));
        }

        if (currentTime <= 5 || (currentTime <= 30 && currentTime % 10 == 0) || currentTime % 60 == 0) {
            gameHandler.broadcast("lobby-count");
        }
    }

    public void cancel() {
        super.cancel();
        gameHandler.setGameState(GameState.PREGAME);
    }

}
