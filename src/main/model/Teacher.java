package model;

import java.util.HashMap;
import java.util.Scanner;

public class Teacher {
    private HashMap<Integer, String> beats = new HashMap<>();

    public Teacher() {
        this.beats.put(1, "df");
        this.beats.put(2, "djfj");
    }

    public String getBeat(int i) {
        return beats.get(i);
    }

    public int checkCorrectness(int currTime, String beat, Character key) {
        if (key == beat.charAt(currTime % beat.length())) {
            return 1;
        } else {
            return 0;
        }

    }
}
