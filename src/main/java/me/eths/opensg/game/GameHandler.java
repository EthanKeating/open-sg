package me.eths.opensg.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.game.runnable.*;
import me.eths.opensg.game.settings.GameSettings;
import me.eths.opensg.game.state.GameState;
import me.eths.opensg.game.timer.GameTimer;
import me.eths.opensg.game.updater.IUpdater;
import me.eths.opensg.game.updater.SidebarUpdater;
import me.eths.opensg.lang.LanguageHandler;
import me.eths.opensg.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameHandler {

    private final SGPlugin plugin;
    private BukkitRunnable currentRunnable;
    @Getter private GameState gameState;
    @Getter private GameTimer gameTimer;
    @Getter private GameSettings gameSettings;

    @Getter private ArrayList<UUID> tributeList = new ArrayList<>();
    @Getter private ArrayList<UUID> spectatorList = new ArrayList<>();
    @Getter private ArrayList<UUID> playerList = new ArrayList<>();

    private final ArrayList<IUpdater> updaterList = new ArrayList<>();

    public GameHandler(SGPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() { //Used as init, but also used as reinit
        this.gameTimer = new GameTimer();
        this.gameSettings = new GameSettings();
        setGameState(GameState.LOBBY);

        updaterList.add(new SidebarUpdater());

        //Please remember to change this to a better method
        //I also dont want this to be reinit'd every time, can probably put this in the constructor
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            updaterList.forEach(updater -> updater.update(plugin));
        }, 20L, 20L);
    }

    public void setGameState(GameState newState) {
        gameState = newState;

        if (currentRunnable != null)
            currentRunnable.cancel();

        switch(newState) {
            case LOBBY:
                currentRunnable = new LobbyRunnable(plugin); break;
            case PREGAME:
                currentRunnable = new PreGameRunnable(plugin); break;
            case LIVEGAME:
                currentRunnable = new LiveGameRunnable(plugin); break;
            case PREDEATHMATCH:
                currentRunnable = new PreDeathmatchRunnable(plugin); break;
            case DEATHMATCH:
                currentRunnable = new DeathmatchRunnable(plugin); break;
            case CLEANUP:
                currentRunnable = new CleanupRunnable(plugin); break;
        }

        if (currentRunnable != null)
            currentRunnable.runTaskTimer(plugin, 0L, 20L);
    }

    public void broadcast(String messagePath) {
        LanguageHandler languageHandler = plugin.getLanguageHandler();
                getPlayerList()
                .stream()
                .map(Bukkit::getPlayer)
                .forEach(player -> {
                    FileConfiguration fileConfiguration = languageHandler.getLanguageFile(LanguageHandler.DEFAULT_LANGUAGE);

                    player.sendMessage(TextUtil.translate(fileConfiguration.getString(messagePath)));
                });
    }



    private void stop() {

    }

}
