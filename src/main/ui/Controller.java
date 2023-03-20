package ui;

import model.Drums;
import model.Player;
import model.Record;
import model.Teacher;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Represents a controller that handles all the events
 */
public class Controller extends JFrame {
    private Drums drums;
    private Player player;
    private Teacher teacher;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int songsToSkip = 0;
    private static final String SONG_LIST = "./data/songs.json";
    private long lastKnownTime;

    public Controller() {
        drums = new Drums();
        player = new Player();
        teacher = new Teacher();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(SONG_LIST);
        jsonReader = new JsonReader(SONG_LIST);
        lastKnownTime = 0;
    }


    // EFFECTS: play the sound that corresponds to the key
    public boolean playSound(Character key) {
        boolean noError = true;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(drums.getSound(key));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (NullPointerException | UnsupportedAudioFileException | IOException e) {
            noError = false;
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        return noError;
    }


    //EFFECTS: plays a recorded song based on user's input
    public void mainPlayRecord(int songNum) throws InterruptedException {
        Record tempRecord;
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < player.getSong(songNum).size(); i++) {
            tempRecord = player.getRecord(i, songNum);
            playSound(tempRecord.getKey());
            TimeUnit.MILLISECONDS.sleep(tempRecord.getTime());
        }
    }


    // MODIFIES: this (specifically modifies the players object)
    // EFFECTS: records a song based on what the user presses and when
    public void mainRecord(char key) {
        boolean isFirst = player.getRecords().size() == 0;
        boolean noErrors = false;

        noErrors = playSound(key);
        if (noErrors) {
            if (isFirst) {
                player.record(key, 0);
            } else {
                long elapsedTime = System.currentTimeMillis() - lastKnownTime;
                player.record(key, elapsedTime);
            }
            lastKnownTime = System.currentTimeMillis();
        }

    }

    public void mainAddRecords() {
        System.out.println(player.getRecords());
        player.addToSongs();
        player.deleteRecords();
    }


    // EFFECT: gives the user an option to play random keys until 'q' is pressed


    // EFFECTS: calculates the percentage of the keys that user presses are equal to what the user should be
    //          pressing based on what beat was selected and how many times was it supposed to be played
    public void mainLearn() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        double correctCount = 0;
        System.out.println("Choose the beat you wish to play (1-3): ");
        int beatNum = scanner.nextInt();
        while (beatNum < 1 || beatNum > 3) {
            System.out.println("Not a valid beat number, choose again (1-3): ");
            beatNum = scanner.nextInt();
        }
        System.out.println("Choose the number of times you wish to play this (recommended between 4 to 8): ");
        int times = scanner.nextInt();

        String beat = teacher.getBeat(beatNum);
        double total = beat.length() * times;
        System.out.println("You'll play '" + beat + "' " + times + " times! Play each character individually");

        for (int i = 0; i < total; i++) {
            Character key = scanner.next().charAt(0);
            playSound(key);
            if (teacher.checkCorrectness(i, beat, key) == 1) {
                System.out.println("Correct!");
                correctCount += 1;
            } else {
                System.out.println("Wrong!");
            }
        }
        System.out.println("You correctness is: " + (correctCount / total) * 100 + "%!");

    }


    // EFFECTS: displays which keys map to which part of the drum-set
    public void showDrumMap() {
        System.out.println("s - Floor Tom");
        System.out.println("d - Base");
        System.out.println("f - Snare");
        System.out.println("g - Small Tom");
        System.out.println("h - Medium Tom");
        System.out.println("j - Hi-Hat");
        System.out.println("k - Crash");

    }


    // EFFECTS: displays a starting guide to help user navigate the program
    public void intro() {
        System.out.println("Please enter: \n"
                + "\t 1  to record\n"
                + "\t 2  to freestyle\n"
                + "\t 3  to learn\n"
                + "\t 4  to listen to your records\n"
                + "\t'h' to see drum mappings\n"
                + "\t'e' to exit\n"
                + "When typing alphabets, only the first character of input in considered, so type carefully!");
    }

    // EFFECTS: Saves the songs in json format in SONG_LIST
    public void mainSave() {
        try {
            Player ogPlayer = jsonReader.read();
            for (int i = songsToSkip + 1; i <= player.getNumOfSongs(); i++) {
                ogPlayer.addNewSong(player.getSong(i));
            }
            // test comment
            jsonWriter.open();
            jsonWriter.write(ogPlayer);
            jsonWriter.close();
            System.out.println("Saved songs to " + SONG_LIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + SONG_LIST);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + SONG_LIST);
        }
    }

    // MODIFIES: this (player)
    // EFFECTS: creates a player object with the songs' field filled using the json data from SONG_LIST
    public void mainLoad() {
        try {
            player = jsonReader.read();
            songsToSkip = player.getNumOfSongs();
            System.out.println("Loaded songs from " + SONG_LIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + SONG_LIST);
        }
    }

    public Player getPlayer() {
        return player;
    }

}
