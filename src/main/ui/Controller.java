package ui;

import model.Drums;
import model.Player;
import model.Record;
import model.Teacher;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

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

    public void mainRecord() throws UnsupportedAudioFileException,
            LineUnavailableException, IOException, InterruptedException {
        Character key = scanner.next().charAt(0);

        System.out.println("Started recording!");
        player.record(key, 0);
        while (true) {
            long startTime = System.currentTimeMillis();

            key = scanner.next().charAt(0);
            if (key == 'q') {
                break;
            }
            long elapsedTime = System.currentTimeMillis() - startTime;


            player.record(key, elapsedTime);
        }
        System.out.println("Ended recording!");

        System.out.println("Replaying!");
        player.play();
    }

    public void mainFreestyle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        char choice;
        System.out.println("Play!");
        while (true) {
            choice = scanner.next().charAt(0);
            if (choice == 'q') {
                break;
            }
            drums.play(choice);
        }
    }

    public void mainLearn() {
        System.out.println("Choose the beat you wish to play (1-2): ");
        int beatNum = scanner.nextInt();
        System.out.println("Choose the number of times you wish to play this: ");
        int times = scanner.nextInt();
        String beat = teacher.getBeat(beatNum);

        System.out.println("You're playing '" + beat + "' " + times + " times!");
        for (int i = 0; i < beat.length() * times; i++) {
            Character key = scanner.next().charAt(0);
            if (teacher.checkCorrectness(i, beat, key)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong!");
            }
        }

    }
}
