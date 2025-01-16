package me.eths.opensg.game.updater;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.game.state.GameState;
import me.eths.opensg.game.timer.GameTimer;
import me.eths.opensg.profile.ProfileHandler;
import me.eths.opensg.time.TimeHandler;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class SidebarUpdater implements IUpdater {

    @Override
    public void update(SGPlugin plugin) {
        GameHandler gameHandler = plugin.getGameHandler();
        ProfileHandler profileHandler = plugin.getProfileHandler();
        TimeHandler timeHandler = plugin.getTimeHandler();
        GameState gameState = gameHandler.getGameState();
        GameTimer gameTimer = gameHandler.getGameTimer();

        FileConfiguration language = plugin.getLanguageHandler().getLanguageFile("en");

        switch (gameState) {
            case LOBBY:
                profileHandler.getProfileMap().values().forEach(profile -> {
                    String title = language.getString("sidebar.Lobby.title")
                            .replace("%timer%", gameHandler.getGameTimer().toString());
//                    List<String> lines = language.getStringList("sidebar.Lobby.lines")
//                            .stream()
//                            .map(string -> timeHandler.replaceDateTime(string, profile.getTimeZone()))
//                            .collect(Collectors.toList());
                    List<String> lines = language.getStringList("sidebar.Lobby.lines");

                    profile.getSidebar().setTitle(title);
                    profile.getSidebar().setLines(lines);
                });
        }
    }

}
