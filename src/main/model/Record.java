package model;

/*
 * Represents a single record with key and time after which it should be played
 */
public class Record {
    private char key;
    private long time;

    public Record(char key, long time) {
        this.key = key;
        this.time = time;
    }


    // Effects: Returns the key in character of the record
    public char getKey() {
        return this.key;
    }


    // Effects: Returns the time in integer of the record
    public int getTime() {
        return (int) this.time;
    }
}
