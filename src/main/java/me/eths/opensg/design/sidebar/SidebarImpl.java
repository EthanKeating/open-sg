package me.eths.opensg.design.sidebar;

import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDisplayScoreboard;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerScoreboardObjective;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTeams;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateScore;
import me.eths.opensg.profile.Profile;
import me.eths.opensg.util.Pair;
import me.eths.opensg.util.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    private final Profile profile;
    private String title = "";
    private List<String> lines = new ArrayList<>(); //Remove storing lines, just use lines size.
    private boolean zeroBoard;

    public SidebarImpl(Profile profile) {
        this.profile = profile;
        //zeroBoard = !profile.isLegacy();
        this.init();
    }

    public SidebarImpl setTitle(String newTitle) {
        newTitle = newTitle.substring(0, Math.min(newTitle.length(), 32));
        if (this.title.equals(newTitle)) return this;

        User user = profile.getUser();

        this.title = newTitle;
        user.sendPacket(new WrapperPlayServerScoreboardObjective(
                SB_NAME,
                WrapperPlayServerScoreboardObjective.ObjectiveMode.UPDATE,
                Component.text(ChatColor.translateAlternateColorCodes('&', title)), //Title
                WrapperPlayServerScoreboardObjective.RenderType.INTEGER));

        return this;
    }
    public SidebarImpl setLines(List<String> newLines) {
        if (!zeroBoard) Collections.reverse(newLines);

        int sizeDifference = newLines.size() - lines.size();
        if (sizeDifference > 0)
            for (int i = 0; i < sizeDifference; i++)
                showLine(lines.size() + i);
        else
            for (int i = 0; i < -sizeDifference; i++)
                hideLine(lines.size() - 1 - i);

        for (int i = 0; i < newLines.size(); i++)
            setLine(i, newLines.get(i));

        lines = newLines;
        return this;
    }

    private void setLine(int lineId, String lineText) {
        if (lineId < lines.size() && !lines.isEmpty())
            if (lines.get(lineId).equals(lineText))
                return;

        User user = profile.getUser();

        String teamName = SB_LINE_NAME + lineId;
        Pair<String, String> splitLine = TextUtil.splitLine(lineText);

        user.sendPacket(
                new WrapperPlayServerTeams(
                        teamName,
                        WrapperPlayServerTeams.TeamMode.UPDATE,
                        new WrapperPlayServerTeams.ScoreBoardTeamInfo(
                                Component.text(""),
                                Component.text(splitLine.getKey()), //prefix
                                Component.text(splitLine.getValue()), //suffix
                                WrapperPlayServerTeams.NameTagVisibility.ALWAYS,
                                WrapperPlayServerTeams.CollisionRule.ALWAYS,
                                NamedTextColor.WHITE,
                                WrapperPlayServerTeams.OptionData.NONE
                        ),
                        Collections.emptyList()
                )
        );
    }

    private void init() {
        User user = profile.getUser();
        user.sendPacket(new WrapperPlayServerScoreboardObjective(
                SB_NAME,
                WrapperPlayServerScoreboardObjective.ObjectiveMode.CREATE, Component.text(""), //Title
                WrapperPlayServerScoreboardObjective.RenderType.INTEGER));

        user.sendPacket(new WrapperPlayServerDisplayScoreboard(1, SB_NAME));

        for (int lineId = 0; lineId < 15; lineId++) {

            String dummyName = ChatColor.translateAlternateColorCodes('&', "&" + CODES[lineId]);
            String teamName = SB_LINE_NAME + lineId;

            user.sendPacket( //No reason to create or delete a team whenever a line is hidden or shown, just add all teams first
                    new WrapperPlayServerTeams(
                            teamName,
                            WrapperPlayServerTeams.TeamMode.CREATE,
                            new WrapperPlayServerTeams.ScoreBoardTeamInfo(
                                    Component.text(""), //displayname - not required?
                                    Component.text(""), //prefix
                                    Component.text(""), //suffix
                                    WrapperPlayServerTeams.NameTagVisibility.ALWAYS,
                                    WrapperPlayServerTeams.CollisionRule.ALWAYS,
                                    NamedTextColor.WHITE,
                                    WrapperPlayServerTeams.OptionData.NONE
                            ),
                            Collections.singletonList(dummyName)
                    )
            );
        }
    }


    private void hideLine(int lineId) {
        User user = profile.getUser();

        String dummyName = ChatColor.translateAlternateColorCodes('&', "&" + CODES[lineId]);

        user.sendPacket(
                new WrapperPlayServerUpdateScore(
                        dummyName,
                        WrapperPlayServerUpdateScore.Action.REMOVE_ITEM,
                        SB_NAME,
                        Optional.of(zeroBoard ? 0 : lineId)
                )
        );
    }

    private void showLine(int lineId) {
        User user = profile.getUser();

        String dummyName = ChatColor.translateAlternateColorCodes('&', "&" + CODES[lineId]);

        user.sendPacket(
                new WrapperPlayServerUpdateScore(
                        dummyName,
                        WrapperPlayServerUpdateScore.Action.CREATE_OR_UPDATE_ITEM,
                        SB_NAME,
                        Optional.of(zeroBoard ? 0 : lineId)
                )
        );
    }
}
