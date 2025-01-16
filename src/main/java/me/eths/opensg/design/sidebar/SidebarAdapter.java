package me.eths.opensg.design.sidebar;

import me.eths.opensg.design.IAdapter;
import me.eths.opensg.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public abstract class SidebarAdapter implements IAdapter {

    public static SidebarAdapter DEFAULT = new SidebarAdapter() {
        public void tick() {}

        public int getUpdateRate() { return 20; }

        public String getTitle(Profile profile) { return ""; }

        public List<String> getLines(Profile profile) { return new ArrayList<>(); }
    };

    public abstract String getTitle(Profile profile);

    public abstract List<String> getLines(Profile profile);
}
