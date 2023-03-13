package model;

import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a single record with key and time after which it should be played
 */
public class Record implements Writable {
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


    // REFERENCE : code below was referred from the following project :
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    @Override
    // Effects: converts the record object into a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("key", Character.toString(key));
        json.put("time", time);
        return json;
    }
}
