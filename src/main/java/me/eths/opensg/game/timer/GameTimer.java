package me.eths.opensg.game.timer;


import lombok.Getter;
import lombok.Setter;
import me.eths.opensg.util.Pair;

@Getter
public class GameTimer {

    private int currentTime;
    private int initialTime;

    public void setTime(int newTime) {
        currentTime = newTime;
        initialTime = newTime;
    }

    public int decrement(){
        return --currentTime;
    }

    public int reset() {
        currentTime = initialTime;
        return currentTime;
    }

    public String toString() {
        return String.format("%d:%02d", currentTime / 60, currentTime % 60);
    }

    public String toString(DisplayType displayType) {
        switch (displayType) {
            case CLOCK_FACE:
                return String.format("%d:%02d", currentTime / 60, currentTime % 60);
            case COMPACT_LABEL:
                return String.format("%d%s %02dm", currentTime / 60, currentTime / 60 > 0 ? "" : "m", currentTime % 60);
        }
        return toString();
    }

    public Pair<Integer, String> toSignificantUnit() {
        if (currentTime < 60)
            return new Pair<>(currentTime, currentTime == 1 ? "second" : "seconds");

        int minutes = currentTime / 60;
        return new Pair<>(minutes, minutes == 1 ? "minute" : "minutes");
    }
}

