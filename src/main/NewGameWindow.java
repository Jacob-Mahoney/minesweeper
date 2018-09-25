package main;

import javax.swing.*;
import java.awt.*;

class NewGameWindow extends JFrame {

    NewGameWindow() {
        super("Hey");
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {

        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // end program when this frame is closed

        text1.setMinimumSize(new Dimension(200, 30));
        text1.setPreferredSize(new Dimension(200, 30));
        text1.setMaximumSize(new Dimension(200, 30));

        // hand-coded grouplayout stuff
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(text1)
                                .addComponent(text2)
                                .addComponent(text3))
                        .addGap(25))
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(text1)
                        .addGap(15)
                        .addComponent(text2)
                        .addGap(15)
                        .addComponent(text3)
                        .addGap(25))
        );

        pack();

    }

}