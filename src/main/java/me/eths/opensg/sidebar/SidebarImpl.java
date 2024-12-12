package me.eths.opensg.sidebar;

import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTeams;
import me.eths.opensg.reflection.impl.RScoreboardTeam;
import me.eths.opensg.reflection.impl.RTeamPacket;
import me.eths.opensg.util.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class SidebarImpl {


    private static final char[] CODES = new char[] {
            '0', '1', '2',
            '3', '4', '5',
            '6', '7', '8',
            '9', 'a', 'b',
            'c', 'd', 'e'
    };
    private static final String SB_NAME = "SB-COMPONENT";
    private static final String SB_LINE_NAME = "SB-LINE-";

    private final UUID uuid;
    private String title = "";
    private List<String> lines = new ArrayList<>(); //Remove storing lines, just use lines size.
    private boolean zeroBoard;

    public SidebarImpl(UUID uuid) {
        this.uuid = uuid;
        //zeroBoard = !profile.isLegacy();
        this.init();
    }

    public Player toBukkit() {
        return Bukkit.getPlayer(uuid);
    }

    private void init() {
        for (int lineId = 0; lineId < 15; lineId++) {

            RScoreboardTeam scoreboardTeam = new RScoreboardTeam(
                    SB_LINE_NAME + lineId,
                    "",
                    "",
                    Collections.singletonList(TextUtil.translate("&" + CODES[lineId]))
            );

            new RTeamPacket(0, scoreboardTeam).sendPacket(toBukkit());
        }
    }

}
