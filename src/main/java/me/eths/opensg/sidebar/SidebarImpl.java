package me.eths.opensg.sidebar;

import me.eths.opensg.reflection.Reflection;
import me.eths.opensg.util.Pair;
import me.eths.opensg.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class SidebarImpl extends Reflection {
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
        sendPacket(toBukkit(), createObjectivePacket(0, SB_NAME, ""));
        sendPacket(toBukkit(), createObjectiveDisplay(SB_NAME, 1));

        for (int lineId = 0; lineId < 15; lineId++) {
            sendPacket(toBukkit(), createTeamPacket(
                    SB_LINE_NAME + lineId,
                    Collections.singletonList(TextUtil.translate( "&" + CODES[lineId])),
                    0,
                    "",
                    ""
            ));
        }
    }

    public Player toBukkit() {
        return Bukkit.getPlayer(uuid);
    }

    public void setTitle(String newTitle) {
        newTitle = newTitle.substring(0, Math.min(newTitle.length(), 32));
        if (title.equals(newTitle)) return;

        sendPacket(toBukkit(), createObjectivePacket(2, SB_NAME, TextUtil.translate(title)));
    }

    public SidebarImpl setLines(List<String> newLines) {
        if (!zeroBoard)
            Collections.reverse(newLines);

        int sizeDifference = newLines.size() - lines.size();

        for (int i = 0; i < Math.abs(sizeDifference); i++) {
            if (sizeDifference > 0)
                showLine(lines.size() + i);
            else
                hideLine(lines.size() - 1 - i);
        }

        for (int lineId = 0; lineId < newLines.size(); lineId++)
            setLine(lineId, newLines.get(lineId));

        lines = newLines;
        return this;
    }

    private void setLine(int lineId, String lineText) {
        if (lineId < lines.size() && !lines.isEmpty())
            if (lines.get(lineId).equals(lineText))
                return;

        Pair<String, String> splitLine = TextUtil.splitLine(lineText);

        sendPacket(toBukkit(), createTeamPacket(
                SB_LINE_NAME + lineId,
                Collections.emptyList(),
                2,
                splitLine.getKey(),
                splitLine.getValue()
        ));
    }

    private void showLine(int lineId) {
        sendPacket(toBukkit(), createScorePacket(
                TextUtil.translate( "&" + CODES[lineId]),
                SB_NAME,
                zeroBoard ? 0 : lineId,
                0
        ));
    }

    private void hideLine(int lineId) {
        sendPacket(toBukkit(), createScorePacket(
                TextUtil.translate( "&" + CODES[lineId]),
                SB_NAME,
                zeroBoard ? 0 : lineId,
                1
        ));
    }
}
