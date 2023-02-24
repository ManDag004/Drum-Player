package model;

public class Record {
    private char key;
    private long time;

    public Record(char key, long time) {
        this.key = key;
        this.time = time;
    }

    public char getKey() {
        return this.key;
    }

    public int getTime() {
        return (int) this.time;
    }
}
