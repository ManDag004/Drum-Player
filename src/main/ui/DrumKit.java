package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
 * Represents a frame that takes user's actions and calls the appropriate method from controller
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

    // EFFECTS: Initializes the DrumKet JFrame
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public DrumKit() {
        super("Drum Kit");
        addKeyListener(this);
        controller = new Controller();
        mode = 1;

        initGaphics();

        int result = JOptionPane.showConfirmDialog(this,"Do you wish to load?", "Load",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            controller.mainLoad();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all the visual component in the window
    private void initGaphics() {
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
    }


    // MODIFIES: this
    // EFFECTS: creates and adds the buttons
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButtons() {
        recordButton = new JButton(new ImageIcon("src/start.png"));
        recordButton.setBounds(350, 50, 20, 20);
        recordButton.addActionListener(this);
        recordButton.setFocusable(false);
        recordButton.setOpaque(false);
        add(recordButton);

        stopRecordButton = new JButton(new ImageIcon("src/stop.png"));
        stopRecordButton.setBounds(430, 50, 20, 20);
        stopRecordButton.addActionListener(this);
        stopRecordButton.setFocusable(false);
        add(stopRecordButton);

        showSongs = new JButton();
        showSongs.setBounds(200, 300, 100, 20);
        showSongs.addActionListener(this);
        showSongs.setFocusable(false);
        showSongs.setText("Songs");
        recordButton.setBackground(Color.BLACK);
        recordButton.setOpaque(true);
        add(showSongs);

        saveAndExit = new JButton();
        saveAndExit.setBounds(200, 350, 100, 20);
        saveAndExit.addActionListener(this);
        saveAndExit.setFocusable(false);
        saveAndExit.setText("Save and Exit");
        recordButton.setBackground(Color.BLACK);
        recordButton.setOpaque(true);
        add(saveAndExit);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds the labels
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

    // MODIFIES: this
    // EFFECTS: creates timers that correspond with each label
    public void addTimers() {
        timers = new HashMap<>();
        for (char key : drumLabels.keySet()) {
            timers.put(key, new Timer(200, e -> {
                drumLabels.get(key).setVisible(false);
                timers.get(key).stop();
            }));
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the label of the corresponding key on the corresponding drum part
    public void animateLabel(char key) {
        JLabel tempLabel = drumLabels.get(key);
        Timer tempTimer = timers.get(key);

        tempLabel.setVisible(true);
        tempTimer.start();
    }

    // MODIFIES: this (player)
    // EFFECTS: handles the click of all the buttons present on the window
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

    // EFFECTS: Not using it, but has to be present
    @Override
    public void keyTyped(KeyEvent e) {}

    // EFFECTS: deletes all songs
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

    // EFFECTS: Not using it, but has to be present
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new DrumKit();
    }


}


