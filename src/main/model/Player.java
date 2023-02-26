package model;

import java.util.ArrayList;
import java.util.HashMap;


public class Player {
    private ArrayList<Record> records;
    private HashMap<Integer, ArrayList<Record>> songs;

    public Player() {
        this.records = new ArrayList<>();
        this.songs = new HashMap<>();
    }

    public ArrayList<Record> getSong(int i) {
        return songs.get(i);
    }

    public void addToSongs() {
        songs.put(songs.size() + 1, new ArrayList<>(records));
    }

    public void deleteRecords() {
        records = new ArrayList<>();
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    // EFFECTS: Plays every record in records based on when it is supposed to be played
    public Record getRecord(int i, int songNum) {
        records = getSong(songNum);
        if (i == records.size() - 1) {
            return new Record(records.get(i).getKey(), records.get(0).getTime());
        }
        return new Record(records.get(i).getKey(), records.get(i + 1).getTime());
    }

    // MODIFIES: this
    // EFFECTS: Stores the keys pressed and the time between each key as Records
    public void record(Character key, long elapsedTime) {
        records.add(new Record(key, elapsedTime));
    }

}
