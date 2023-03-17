package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prompts extends JFrame implements ActionListener {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 150;
    private int option;
    JButton yesButton;
    JButton noButton;
    Controller controller;


    public Prompts(Controller controller, int i) {
        super("Dialog Box");
        option = i;
        this.controller = controller;
        initGraphics();
        addButons();
    }

    private void initGraphics() {
        setVisible(true);
        setLayout(null);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        centreOnScreen();
    }

    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    private void addButons() {
        yesButton = new JButton();
        yesButton.setBounds(30, 65, 60, 20);
        yesButton.addActionListener(this);
        yesButton.setFocusable(false);
        yesButton.setText("Yes");
        add(yesButton);


        noButton = new JButton();
        noButton.setBounds(110, 65, 60, 20);
        noButton.addActionListener(this);
        noButton.setFocusable(false);
        noButton.setText("No");
        add(noButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (option == 0) {
            if (e.getSource() == yesButton) {
                controller.mainLoad();
            }
            this.dispose();
        } else {
            if (e.getSource() == yesButton) {
                controller.mainSave();
                System.exit(0);
            }
            this.dispose();

        }
    }
}
