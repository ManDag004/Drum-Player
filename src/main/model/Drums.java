package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Drums {
    // delete or rename this class!
    private static HashMap<Character, File> drumParts = new HashMap<Character, File>();

    public Drums() {
        drumParts.put('d', new File("src/Bass.wav"));
        drumParts.put('f', new File("src/Snare.wav"));
        drumParts.put('j', new File("src/Hi-Hat.wav"));
        drumParts.put('k', new File("src/Crash.wav"));
    }

    // REQUIRES: key should be in the HashMap drumParts
    // EFFECTS: plays the sound corresponding to the key entered by search for it in the HashMap
    public void play(char key) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(drumParts.get(key));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }
}
