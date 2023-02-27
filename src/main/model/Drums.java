package model;


import java.io.File;
import java.util.HashMap;


/*
 * Represents a drum-set
 */
public class Drums {

    private static HashMap<Character, File> drumParts = new HashMap<>();


    // EFFECTS: constructs a new Drums object
    public Drums() {
        drumParts.put('d', new File("src/Bass.wav"));
        drumParts.put('f', new File("src/Snare.wav"));
        drumParts.put('j', new File("src/Hi-Hat.wav"));
        drumParts.put('k', new File("src/Crash.wav"));
        drumParts.put('s', new File("src/Floor-Tom.wav"));
        drumParts.put('g', new File("src/Small-Tom.wav"));
        drumParts.put('h', new File("src/Medium-Tom.wav"));
    }


    // REQUIRES: key should be in the HashMap drumParts
    // EFFECTS: plays the sound corresponding to the key entered by search for it in the HashMap
    public File getSound(char key) {
        return drumParts.get(key);
    }

}
