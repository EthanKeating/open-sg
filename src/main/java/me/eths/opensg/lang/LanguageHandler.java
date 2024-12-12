package me.eths.opensg.lang;

import lombok.AllArgsConstructor;
import me.eths.opensg.SGPlugin;
import me.eths.opensg.util.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

public class LanguageHandler {
    public static final String DEFAULT_LANGUAGE = "en";

    private final HashMap<String, FileConfiguration> languageMap = new HashMap<>();
    private final SGPlugin plugin;

    public LanguageHandler(SGPlugin plugin) {
        this.plugin = plugin;

        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdirs();

        File configFile = Paths.get(plugin.getDataFolder().getPath(), "lang" + DEFAULT_LANGUAGE, ".yml").toFile();
        if (!configFile.exists())
            plugin.saveResource(Paths.get("lang", DEFAULT_LANGUAGE + ".yml").toString(), false);

        languageMap.put(DEFAULT_LANGUAGE, YamlConfiguration.loadConfiguration(configFile));
    }

    public FileConfiguration getLanguageFile(String languageAbbreviation) {
        return languageMap.getOrDefault(languageAbbreviation, languageMap.get(DEFAULT_LANGUAGE));
    }

    public void sendMessage(Player player, String messagePath) {
        //player.language
        FileConfiguration fileConfiguration = getLanguageFile(DEFAULT_LANGUAGE);

        player.sendMessage(TextUtil.translate(fileConfiguration.getString(messagePath)));
    }

}
