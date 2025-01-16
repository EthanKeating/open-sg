package me.eths.opensg;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import me.eths.opensg.game.GameHandler;
import me.eths.opensg.lang.LanguageHandler;
import me.eths.opensg.listener.ListenerHandler;
import me.eths.opensg.packet.PacketHandler;
import me.eths.opensg.profile.ProfileHandler;
import me.eths.opensg.time.TimeHandler;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SGPlugin extends JavaPlugin {

    private GameHandler gameHandler;
    private LanguageHandler languageHandler;
    private ListenerHandler listenerHandler;
    private ProfileHandler profileHandler;
    private TimeHandler timeHandler;

    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    public void onEnable() {
        PacketEvents.getAPI().getEventManager().registerListener(
                new PacketHandler(), PacketListenerPriority.NORMAL);

        PacketEvents.getAPI().init();

        languageHandler = new LanguageHandler(this);
        listenerHandler = new ListenerHandler(this);
        profileHandler = new ProfileHandler(this);
        timeHandler = new TimeHandler(this);
        gameHandler = new GameHandler(this);

        gameHandler.init();
    }

    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }

}
