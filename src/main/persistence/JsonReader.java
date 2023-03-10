package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        Player player = new Player();
        JSONArray jsonArray = jsonObject.getJSONArray("songs");

        for (Object json : jsonArray) {
            addRecords(player, (JSONObject) json);
        }
        return player;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addRecords(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        for (Object json : jsonArray) {
            JSONObject nextRecord = (JSONObject) json;
            addRecord(player, nextRecord);
        }
        player.addToSongs();
        player.deleteRecords();
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addRecord(Player player, JSONObject jsonObject) {
        Character key = jsonObject.getString("key").charAt(0);
        long time = jsonObject.getLong("time");
        player.record(key, time);
    }
}
