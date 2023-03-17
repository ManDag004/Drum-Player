package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Represents the main program that takes user's input and calls the appropriate method from controller
 */
public class DrumKit extends JFrame implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    Controller controller;
    JButton recordButton;
    JButton stopRecordButton;
    JButton showSongs;
    JButton saveAndExit;
    int mode; // 1 to play freely, 2 to record every that is being played

    public DrumKit() {
        super("Drum Kit");
        initGraphics();
        addButons();
        addKeyListener(this);
        controller = new Controller();
        mode = 1;
        new Prompts(controller, 0);
    }



    private void initGraphics() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        centreOnScreen();
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButons() {
        recordButton = new JButton(new ImageIcon("src/main/ui/start.png"));
        recordButton.setBounds(350, 50, 20, 20);
        recordButton.addActionListener(this);
        recordButton.setFocusable(false);
        recordButton.setBackground(Color.RED);
        recordButton.setOpaque(true);
        add(recordButton);

        stopRecordButton = new JButton(new ImageIcon("src/main/ui/stop.png"));
        stopRecordButton.setBounds(430, 50, 20, 20);
        stopRecordButton.addActionListener(this);
        stopRecordButton.setFocusable(false);
        stopRecordButton.setText("Stop");
        add(stopRecordButton);

        showSongs = new JButton();
        showSongs.setBounds(200, 300, 60, 20);
        showSongs.addActionListener(this);
        showSongs.setFocusable(false);
        showSongs.setText("Songs");
        add(showSongs);

        saveAndExit = new JButton();
        saveAndExit.setBounds(200, 350, 60, 20);
        saveAndExit.addActionListener(this);
        saveAndExit.setFocusable(false);
        saveAndExit.setText("Save and Exit");
        add(saveAndExit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recordButton) {
            mode = 2;
        } else if (e.getSource() == stopRecordButton) {
            mode = 1;
            controller.mainAddRecords();
        } else if (e.getSource() == showSongs) {
            new SavedSongs(controller);
        } else if (e.getSource() == saveAndExit) {
            new Prompts(controller, 1);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (mode == 1) {
            controller.playSound(key);
        } else if (mode == 2) {
            controller.mainRecord(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



    // EFFECTS: runs the program and handles all user interactions
//    public static void main(String[] args) throws UnsupportedAudioFileException,
//            LineUnavailableException, IOException, InterruptedException {
//        Controller controller = new Controller();
//        Scanner scanner = new Scanner(System.in);
//
//        controller.mainLoad();
//        controller.intro();
//
//        char choice = scanner.next().charAt(0);
//
//        while (choice != 'e') {
//            if (choice == '1') {
//                controller.mainRecord();
//            } else if (choice == '2') {
//                controller.mainFreestyle();
//            } else if (choice == '3') {
//                controller.mainLearn();
//            } else if (choice == '4') {
//                controller.mainPlayRecord();
//            } else if (choice == 'h') {
//                controller.showDrumMap();
//            }
//
//            controller.intro();
//            choice = scanner.next().charAt(0);
//        }
//
//        controller.mainSave();
//        System.out.println("Quitting!");
//    }

    public static void main(String[] args) {
        new DrumKit();
    }


}


