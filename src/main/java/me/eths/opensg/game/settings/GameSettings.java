package me.eths.opensg.game.settings;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class GameSettings {

    private int minimumPlayers = 6;
    private int maximumPlayers = 24;

    private int lobbyLength = 60;
    private int preGameLength = 30;
    private int liveGameLength = 30 * 60; //30 minutes
    private int preDeathmatchTime = 10;
    private int deathmatchTime = 3 * 60; //3 minutes
    private int cleanupTime = 10;

}
