package ui;

import model.Player;
import model.Record;

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
        setVisible(true);
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        centreOnScreen();
        addButtons();
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    private void addButtons() {
        for (int i = 1; i <= controller.getPlayer().getNumOfSongs(); i++) {
            JButton tempButton = new JButton();
            tempButton.setBounds(200, i * 30, 60, 20);
            tempButton.addActionListener(this);
            tempButton.setFocusable(false);
            tempButton.setName(String.valueOf(i));
            tempButton.setText("Song" + i);
            add(tempButton);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        try {
            controller.mainPlayRecord(Integer.parseInt(button.getName()));
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
