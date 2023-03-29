package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/*
 * Represents a Player to record, store and replay music
 */
public class Player implements Writable {
    private ArrayList<Record> records;
    private HashMap<Integer, ArrayList<Record>> songs;

    // Effects: Constructs a Player object with records and songs initialised to empty
    public Player() {
        this.records = new ArrayList<>();
        this.songs = new HashMap<>();
    }


    // EFFECTS: returns the recorded song at the given index
    public ArrayList<Record> getSong(int i) {
        return songs.get(i);
    }


    // MODIFIES: this
    // EFFECTS: Adds the new recorded song to the hashmap at next key value (calculated by adding 1 to its size)
    public void addToSongs() {
        songs.put(songs.size() + 1, new ArrayList<>(records));
    }

    public void addNewSong(ArrayList<Record> song) {
        this.records = song;
        addToSongs();
    }


    // MODIFIES: this
    // EFFECTS: Modifies he records ArrayList as a new empty ArrayList
    public void deleteRecords() {
        records = new ArrayList<>();
    }

    public void deleteRecords(int i) {
        songs.remove(i);
        ArrayList<ArrayList<Record>> tempSongs = new ArrayList<>(songs.values());
        songs.clear();
        for (i = 0; i < tempSongs.size(); i++) {
            songs.put(i + 1, tempSongs.get(i));
        }
    }


    // EFFECTS: returns the records ArrayList
    public ArrayList<Record> getRecords() {
        return records;
    }


    // REQUIRES: i and songNum should be in the range
    // EFFECTS: returns the ith record with key and the amount of time to wait after playing it from the given song
    public Record getRecord(int i, int songNum) {
        records = getSong(songNum);
        if (i == records.size() - 1) {
            return new Record(records.get(i).getKey(), records.get(0).getTime());
        }
        return new Record(records.get(i).getKey(), records.get(i + 1).getTime());
    }


    // REQUIRES: elapsedTime >= 0 and key should correspond to a drum part
    // MODIFIES: this
    // EFFECTS: Stores the keys pressed and the time between each key as Records
    public void record(Character key, long elapsedTime) {
        records.add(new Record(key, elapsedTime));
    }


    // EFFECTS: return the number of songs that have been stored
    public int getNumOfSongs() {
        return songs.size();
    }

    // REFERENCE : code below was referred from the following project :
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    @Override
    // Effects: converts the player object into JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("songs", songsToJson());
        return json;
    }

    // Effects: converts the songs HashMap object into JSONArray
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 1; i <= songs.size(); i++) {
            JSONObject json = new JSONObject();
            json.put("id", i);
            json.put("records", recordsToJson(songs.get(i)));
            jsonArray.put(json);
        }

        return jsonArray;
    }

    // Effects: converts a ArrayList<Record> records into JSONArray
    private JSONArray recordsToJson(ArrayList<Record> records) {
        JSONArray jsonArray = new JSONArray();

        for (Record r : records) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
