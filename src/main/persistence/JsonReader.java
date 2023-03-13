package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import org.json.*;

// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

/*
 * Represents a reader that reads progress from JSON data stored in file
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads player from file and returns it;
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

    // EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        Player player = new Player();
        JSONArray jsonArray = jsonObject.getJSONArray("songs");

        for (Object json : jsonArray) {
            addRecords(player, (JSONObject) json);
        }
        return player;
    }

    // MODIFIES: player
    // EFFECTS: parses records from JSON object and adds them to player
    private void addRecords(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        for (Object json : jsonArray) {
            JSONObject nextRecord = (JSONObject) json;
            addRecord(player, nextRecord);
        }
        player.addToSongs();
        player.deleteRecords();
    }

    // MODIFIES: player
    // EFFECTS: parses a single record from JSON object and adds it to player
    private void addRecord(Player player, JSONObject jsonObject) {
        Character key = jsonObject.getString("key").charAt(0);
        long time = jsonObject.getLong("time");
        player.record(key, time);
    }
}
