package me.eths.opensg.game.updater;

import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.game.state.GameState;
import me.eths.opensg.game.timer.GameTimer;
import me.eths.opensg.profile.ProfileHandler;
import org.bukkit.configuration.file.FileConfiguration;

public class SidebarUpdater implements IUpdater {

    @Override
    public void update(SGPlugin plugin) {
        GameHandler gameHandler = plugin.getGameHandler();
        ProfileHandler profileHandler = plugin.getProfileHandler();
        GameState gameState = gameHandler.getGameState();
        GameTimer gameTimer = gameHandler.getGameTimer();

        FileConfiguration language = plugin.getLanguageHandler().getLanguageFile("en");

        switch (gameState) {
            case LOBBY:
                profileHandler.getProfileMap().values().forEach(profile -> {
                    profile.getSidebar().setTitle(language.getString("Sidebar.Lobby.Title"));
                    profile.getSidebar().setLines(language.getStringList("Sidebar.Lobby.Lines"));
                });
        }
    }

}
