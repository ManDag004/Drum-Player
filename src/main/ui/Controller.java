package ui;

import model.Drums;
import model.Player;
import model.Record;
import model.Teacher;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Represents a controller that handles all the events
 */
public class Controller {
    private Drums drums;
    private Player player;
    private Teacher teacher;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private int songsToSkip = 0;
    private static final String SONG_LIST = "./data/songs.json";

    public Controller() {
        drums = new Drums();
        player = new Player();
        teacher = new Teacher();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(SONG_LIST);
        jsonReader = new JsonReader(SONG_LIST);


    }


    // EFFECTS: play the sound that corresponds to the key
    public boolean playSound(Character key) throws LineUnavailableException,
            IOException, UnsupportedAudioFileException {
        boolean noError = true;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(drums.getSound(key));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (NullPointerException e) {
            noError = false;
        }

        return noError;
    }


    //EFFECTS: plays a recorded song based on user's input
    public void mainPlayRecord() throws InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        int songNum = -1;

        if (player.getNumOfSongs() != 0) {
            while (songNum < 0 || songNum > player.getNumOfSongs()) {
                System.out.println("You have " + player.getNumOfSongs() + " songs. Choose 1 to play your first "
                        + "recording, 2 to play your second recording, and so on. Choose 0 if you wish to exit:");
                songNum = scanner.nextInt();
            }

            if (songNum != 0) {
                Record tempRecord;
                TimeUnit.SECONDS.sleep(1);
                for (int i = 0; i < player.getSong(songNum).size(); i++) {
                    tempRecord = player.getRecord(i, songNum);
                    playSound(tempRecord.getKey());
                    TimeUnit.MILLISECONDS.sleep(tempRecord.getTime());
                }
            }
        } else {
            System.out.println("You currently don't have any recordings. First create some recordings, and then come"
                    + " back here to listen to them.");
        }

    }


    // MODIFIES: this (specifically modifies the players object)
    // EFFECTS: records a song based on what the user presses and when
    public void mainRecord() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Started recording! Press 'q' anytime to stop recording!");

        Character key = scanner.next().charAt(0);
        boolean noErrors = playSound(key);
        player.record(key, 0);

        while (noErrors) {
            long startTime = System.currentTimeMillis();

            key = scanner.next().charAt(0);
            if (key == 'q') {
                break;
            }
            noErrors = playSound(key);
            long elapsedTime = System.currentTimeMillis() - startTime;


            player.record(key, elapsedTime);
        }

        if (noErrors) {
            player.addToSongs();
            System.out.println("Ended recording!");
        } else {
            System.out.println("Invalid key pressed and your recording has been discarded. Next time, play properly!");
        }

        player.deleteRecords();

    }


    // EFFECT: gives the user an option to play random keys until 'q' is pressed
    public void mainFreestyle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        char choice;
        System.out.println("Just Play!");
        System.out.println("Press 'q' anytime to quit!");
        while (true) {
            choice = scanner.next().charAt(0);
            if (choice == 'q') {
                break;
            }
            playSound(choice);
        }
    }


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
                + "\t's' to save your recordings\n"
                + "\t'l' to load your recordings\n"
                + "When typing alphabets, only the first character of input in considered, so type carefully!");
    }

    // EFFECTS: Saves the songs in json format in SONG_LIST
    public void mainSave() {
        System.out.println("Would you like to save your songs from the current session? (y/n)");
        String choice = scanner.next();

        while (!choice.equals("n")) {
            if (choice.equals("y")) {
                try {
                    Player ogPlayer = jsonReader.read();
                    for (int i = songsToSkip + 1; i <= player.getNumOfSongs(); i++) {
                        ogPlayer.addNewSong(player.getSong(i));
                    }
                    jsonWriter.open();
                    jsonWriter.write(ogPlayer);
                    jsonWriter.close();
                    System.out.println("Saved songs to " + SONG_LIST);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file: " + SONG_LIST);
                } catch (IOException e) {
                    System.out.println("Unable to write to file: " + SONG_LIST);
                }
                break;
            }
            System.out.println("Please enter correctly. "
                    + "Would you like to save your songs from the current session? (y/n)");
        }
    }

    // MODIFIES: this (player)
    // EFFECTS: creates a player object with the songs' field filled using the json data from SONG_LIST
    public void mainLoad() {
        System.out.println("Would you like to load your songs from previous sessions? (y/n)");
        String choice = scanner.next();

        while (!choice.equals("n")) {
            if (choice.equals("y")) {
                try {
                    player = jsonReader.read();
                    songsToSkip = player.getNumOfSongs();
                    System.out.println("Loaded songs from " + SONG_LIST);
                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + SONG_LIST);
                }
                break;
            }
            System.out.println("Please enter correctly. "
                    + "Would you like to load your songs from previous sessions? (y/n)");
        }
    }

}
