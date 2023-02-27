package model;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Represents a Player to record, store and replay music
 */
public class Player {
    private ArrayList<Record> records;
    private HashMap<Integer, ArrayList<Record>> songs;

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


    // MODIFIES: this
    // EFFECTS: Modifies he records ArrayList as a new empty ArrayList
    public void deleteRecords() {
        records = new ArrayList<>();
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
}
