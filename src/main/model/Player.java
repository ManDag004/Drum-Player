package model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player {
    private ArrayList<Record> records;
    private Drums drumPlayer;

    public Player() {
        this.records = new ArrayList<Record>();
        this.drumPlayer = new Drums();
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    // EFFECTS: Plays every record in records based on when it is supposed to be played
    public void play() throws UnsupportedAudioFileException,
            LineUnavailableException, IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < records.size() - 1; i++) {
            drumPlayer.play(records.get(i).getKey());
            TimeUnit.MILLISECONDS.sleep(records.get(i + 1).getTime());
            if (i == records.size() - 2) {
                drumPlayer.play(records.get(i + 1).getKey());
            }
        }
        this.records = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Stores the keys pressed and the time between each key as Records
    public void record(Character key, long elapsedTime) throws UnsupportedAudioFileException,
            LineUnavailableException, IOException {
        drumPlayer.play(key);
        records.add(new Record(key, elapsedTime));

    }

}
