package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedSongs extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    Controller controller;

    public SavedSongs(Controller controller) {
        super("Saved Songs");
        this.controller = controller;
        initGraphics();
    }

    private void initGraphics() {
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);

        addButtons();
        setVisible(true);


    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addButtons() {

        for (int i = 1; i <= controller.getPlayer().getNumOfSongs(); i++) {
            JButton tempSongButton = new JButton();
            tempSongButton.setBounds(100, i * 25, 60, 20);
            tempSongButton.addActionListener(this);
            tempSongButton.setFocusable(false);
            tempSongButton.setName(String.valueOf(i));
            tempSongButton.setText("Song " + i);
            add(tempSongButton);

            JButton tempDeleteButton = new JButton();
            tempDeleteButton.setBounds(200, i * 25, 60, 20);
            tempDeleteButton.addActionListener(this);
            tempDeleteButton.setFocusable(false);
            tempDeleteButton.setName(String.valueOf(i));
            tempDeleteButton.setText("Delete " + i);
            add(tempDeleteButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        try {
            if (button.getText().contains("Song")) {
                controller.mainPlayRecord(Integer.parseInt(button.getName()));
            } else {
                controller.removeSong(Integer.parseInt(button.getName()));
                button.setEnabled(false);
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
