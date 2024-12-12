package me.eths.opensg.game.timer;


import lombok.Getter;
import lombok.Setter;

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

    enum DisplayType {
        CLOCK_FACE,
        COMPACT_LABEL
    }
}

