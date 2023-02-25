package model;

import java.util.ArrayList;


public class Player {
    private ArrayList<Record> records;

    public Player() {
        this.records = new ArrayList<>();
    }

    public void deleteRecords() {
        records = new ArrayList<>();
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    // EFFECTS: Plays every record in records based on when it is supposed to be played
    public Record getRecord(int i) {
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
