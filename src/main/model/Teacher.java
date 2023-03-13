package model;

import java.util.HashMap;

/*
 * Represents a teacher that teaches a beat and scores the performance
 */
public class Teacher {
    private HashMap<Integer, String> beats = new HashMap<>();

    // Effects: Constructs a Teacher object with three beats
    public Teacher() {
        this.beats.put(1, "df");
        this.beats.put(2, "djfj");
        this.beats.put(3, "ddgghhssk");
    }


    // EFFECTS: returns the stored beat (in string) at given key value from the HashMap
    public String getBeat(int i) {
        return beats.get(i);
    }


    // EFFECTS: returns 1 if the Character key matches the Character of string beat at the given stage of the learning
    //          process, otherwise 0. If the length of beat is 3, then the value of index 0 will be considered when
    //          currTime is equal of 0, 3, 6, ... and so on.
    public int checkCorrectness(int currTime, String beat, Character key) {
        if (key == beat.charAt(currTime % beat.length())) {
            return 1;
        } else {
            return 0;
        }

    }
}
