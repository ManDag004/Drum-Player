package ui;

import model.Drums;
import model.Player;
import model.Record;
import model.Teacher;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Controller {
    private Drums drums;
    private Player player;
    private Teacher teacher;
    private Scanner scanner;

    public Controller() {
        drums = new Drums();
        player = new Player();
        teacher = new Teacher();
        scanner = new Scanner(System.in);
    }

    public void playSound(Character key) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(drums.getSound(key));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }

    public void mainPlayRecord(int songNum) throws InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        Record tempRecord;
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < player.getSong(songNum).size(); i++) {
            tempRecord = player.getRecord(i, songNum);
            playSound(tempRecord.getKey());
            TimeUnit.MILLISECONDS.sleep(tempRecord.getTime());
        }
        player.deleteRecords();
    }

    public void mainRecord() throws UnsupportedAudioFileException,
            LineUnavailableException, IOException, InterruptedException {
        System.out.println("Started recording!");

        Character key = scanner.next().charAt(0);
        playSound(key);
        player.record(key, 0);

        while (true) {
            long startTime = System.currentTimeMillis();

            key = scanner.next().charAt(0);
            if (key == 'q') {
                break;
            }
            playSound(key);
            long elapsedTime = System.currentTimeMillis() - startTime;


            player.record(key, elapsedTime);
        }
        player.addToSongs();
        player.deleteRecords();
        System.out.println("Ended recording!");
        System.out.println(player.getSong(1).size());
    }

    public void mainFreestyle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        char choice;
        System.out.println("Play!");
        while (true) {
            choice = scanner.next().charAt(0);
            if (choice == 'q') {
                break;
            }
            playSound(choice);
        }
    }

    public void mainLearn() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        double correctCount = 0;
        System.out.println("Choose the beat you wish to play (1-2): ");
        int beatNum = scanner.nextInt();
        System.out.println("Choose the number of times you wish to play this: ");
        int times = scanner.nextInt();
        String beat = teacher.getBeat(beatNum);
        double total = beat.length() * times;
        System.out.println("You're playing '" + beat + "' " + times + " times!");

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
}
