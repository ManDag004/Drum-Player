package ui;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

/*
 * Represents the main program that takes user's input and calls the appropriate method from controller
 */
public class Main {

    // EFFECTS: runs the program and handles all user interactions
    public static void main(String[] args) throws UnsupportedAudioFileException,
            LineUnavailableException, IOException, InterruptedException {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        controller.intro();
        char choice = scanner.next().charAt(0);

        while (choice != 'e') {
            if (choice == '1') {
                controller.mainRecord();
            } else if (choice == '2') {
                controller.mainFreestyle();

            } else if (choice == '3') {
                controller.mainLearn();
            } else if (choice == '4') {
                controller.mainPlayRecord();
            } else if (choice == 'h') {
                controller.showDrumMap();
            }
            controller.intro();
            choice = scanner.next().charAt(0);
        }
        System.out.println("Quitting!");
    }
}


