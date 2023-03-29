package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
    HashMap<Character, JLabel> drumLabels;
    HashMap<Character, Timer> timers;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public DrumKit() {
        // MY CODE
        super("Drum Kit");
        addKeyListener(this);
        controller = new Controller();
        mode = 1;



        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            JLabel bgImg = new JLabel(new ImageIcon(ImageIO.read(new File("src/drum.png"))));
            this.setContentPane(bgImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addButtons();
        addLabels();
        addTimers();

        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        int result = JOptionPane.showConfirmDialog(this,"Do you wish to load?", "Load",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            controller.mainLoad();
        }


//        new Prompts(controller, 0);



        // DIFF CODE
//        try {
//            JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("src/drum.png"))));
//
//            JFrame frame = new JFrame("Testing");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setContentPane(label);
//            frame.setLayout(new BorderLayout());
//            JLabel text = new JLabel("Hello from the foreground");
//            text.setForeground(Color.WHITE);
//            text.setHorizontalAlignment(JLabel.CENTER);
//            frame.add(text);
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        } catch (IOException | HeadlessException exp) {
//            exp.printStackTrace();
//        }
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButtons() {
        recordButton = new JButton(new ImageIcon("src/start.png"));
        recordButton.setBounds(350, 50, 20, 20);
        recordButton.addActionListener(this);
        recordButton.setFocusable(false);
        recordButton.setBackground(Color.RED);
        recordButton.setOpaque(true);
        add(recordButton);

        stopRecordButton = new JButton(new ImageIcon("src/start.png"));
        stopRecordButton.setBounds(430, 50, 20, 20);
        stopRecordButton.addActionListener(this);
        stopRecordButton.setFocusable(false);
        recordButton.setBackground(Color.GRAY);
        recordButton.setOpaque(true);
        add(stopRecordButton);

        showSongs = new JButton();
        showSongs.setBounds(200, 300, 60, 20);
        showSongs.addActionListener(this);
        showSongs.setFocusable(false);
        showSongs.setText("Songs");
        recordButton.setBackground(Color.BLACK);
        recordButton.setOpaque(true);
        add(showSongs);

        saveAndExit = new JButton();
        saveAndExit.setBounds(200, 350, 60, 20);
        saveAndExit.addActionListener(this);
        saveAndExit.setFocusable(false);
        saveAndExit.setText("Save and Exit");
        recordButton.setBackground(Color.BLACK);
        recordButton.setOpaque(true);
        add(saveAndExit);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addLabels() {
        drumLabels = new HashMap<>();
        JLabel crashLabel = new JLabel("CRASH");
        crashLabel.setBounds(100, 100, 60, 20);

        JLabel hiHatLabel = new JLabel("HI-HAT");
        hiHatLabel.setBounds(100, 275, 60, 20);

        JLabel snareLabel = new JLabel("SNARE");
        snareLabel.setBounds(150, 450, 60, 20);

        JLabel baseLabel = new JLabel("BASE");
        baseLabel.setBounds(375, 475, 60, 20);

        JLabel htomLabel = new JLabel("HTOM");
        htomLabel.setBounds(275, 200, 60, 20);

        JLabel mtomLabel = new JLabel("MTOM");
        mtomLabel.setBounds(450, 200, 60, 20);

        JLabel ftomLabel = new JLabel("FTOM");
        ftomLabel.setBounds(600, 450, 60, 20);

        drumLabels.put('k', crashLabel);
        drumLabels.put('j', hiHatLabel);
        drumLabels.put('f', snareLabel);
        drumLabels.put('d', baseLabel);
        drumLabels.put('g', htomLabel);
        drumLabels.put('h', mtomLabel);
        drumLabels.put('s', ftomLabel);

        for (JLabel label : drumLabels.values()) {
            label.setVisible(false);
            add(label);
        }

    }

    public void addTimers() {
        timers = new HashMap<>();
        for (char key : drumLabels.keySet()) {
            timers.put(key, new Timer(200, e -> {
                drumLabels.get(key).setVisible(false);
                timers.get(key).stop();
            }));
        }
    }

    public void animateLabel(char key) {
        JLabel tempLabel = drumLabels.get(key);
        Timer tempTimer = timers.get(key);

        tempLabel.setVisible(true);
        tempTimer.start();
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
            int result = JOptionPane.showConfirmDialog(this,"Do you wish to save?", "Save",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                controller.mainSave();
                System.exit(0);
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        try {
            if (mode == 1) {
                controller.playSound(key);
            } else if (mode == 2) {
                controller.mainRecord(key);
            }
            animateLabel(key);
        } catch (Exception ignored) {
            //
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


