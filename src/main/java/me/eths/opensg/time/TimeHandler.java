package me.eths.opensg.time;

import com.github.retrooper.packetevents.protocol.player.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import me.eths.opensg.SGPlugin;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeHandler {

    private final SGPlugin plugin;

    public TimeHandler(SGPlugin plugin) {
        this.plugin = plugin;
    }

    @SneakyThrows
    public String retrieveTimeZone(Player player) {
        String url = "https://ipinfo.io/" + player.getAddress().getAddress().toString() + "/json";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

            return jsonResponse.has("timezone")
                    ? jsonResponse.get("timezone").getAsString()
                    : TimeZone.getDefault().getDisplayName();
        }
        return TimeZone.getDefault().getDisplayName();
    }
    public String replaceDateTime(String input, String timeZone) {
        String regex = "%time_([a-zA-Z0-9_\\s]+)%"; // Regex to match valid datetime format patterns
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();

        ZoneId zoneId = ZoneId.of(timeZone);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        while (matcher.find()) {
            String dateTimeFormat = matcher.group(1);

            dateTimeFormat = dateTimeFormat.replace("%Y", "yyyy")
                    .replace("%m", "MM")
                    .replace("%d", "dd")
                    .replace("%H", "HH")
                    .replace("%M", "mm")
                    .replace("%S", "ss");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            String formattedTime = zonedDateTime.format(formatter);

            matcher.appendReplacement(result, Matcher.quoteReplacement(formattedTime));

        }
        matcher.appendTail(result);
        return result.toString();
    }
}
