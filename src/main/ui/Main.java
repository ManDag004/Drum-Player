package ui;

import model.Player;
import model.Teacher;
import model.Drums;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException,
            LineUnavailableException, IOException, InterruptedException {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 to record, 2 to freestyle, 3 to learn or 'e' to exit: ");
        Character choice = scanner.next().charAt(0);

        while (choice != 'e') {
            if (choice == '1') {
                controller.mainRecord();
            } else if (choice == '2') {
                controller.mainFreestyle();

            } else if (choice == '3') {
                controller.mainLearn();

            }
            System.out.println("Please enter 1 to record, 2 to freestyle, 3 to learn or 'e' to exit: ");
            choice = scanner.next().charAt(0);
        }
        System.out.println("Quitting!");
//        for (int i = 0; i < 4; i++) {
//            player.add(new Record('j', 125));
//            player.add(new Record('d', 0));
//            player.add(new Record('j', 250));
//            player.add(new Record('d', 0));
//            player.add(new Record('j', 250));
//            player.add(new Record('f', 0));
//            player.add(new Record('j', 250));
//            player.add(new Record('f', 125));
//            player.add(new Record('j', 125));
//            player.add(new Record('f', 125));
//            player.add(new Record('j', 125));
//            player.add(new Record('d', 0));
//            player.add(new Record('j', 250));
//            player.add(new Record('f', 0));
//            player.add(new Record('j', 250));
//            player.add(new Record('f', 125));
//        }
    }
}


