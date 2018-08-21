package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// first frame that is shown on start
public class GameWindow extends JFrame {

    private JPanel grid;
    private JLabel timer;
    private GameGrid gameGrid;

    public GameWindow(GameGrid gameGrid) {
        super("Poop");
        this.gameGrid = gameGrid;
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {

        grid = new JPanel();
        timer = new JLabel("timer");

        GridLayout gridLayout = new GridLayout(9,9);

        grid.setLayout(gridLayout);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        grid.setBackground(Color.WHITE);

        for (int i = 0; i < 81; i++) {

            JButton button = new JButton();

            button.setIcon(ResourceHandler.squareIcon);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    onButtonHoverOver(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    onButtonHoverOut(e);
                }
            });

            button.addActionListener(this::onButtonClick);

            button.setMinimumSize(new Dimension(24, 24));
            button.setPreferredSize(new Dimension(24, 24));
            button.setMaximumSize(new Dimension(24, 24));
            grid.add(button);

        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // end program when this frame is closed

        getContentPane().setBackground(Color.WHITE);

        // hand-coded grouplayout stuff
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(timer)
                                .addGap(15)
                                .addComponent(grid))
                        .addGap(25))
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(timer)
                        .addGap(15)
                        .addComponent(grid)
                        .addGap(25))
        );

        pack();

    }

    private void onButtonClick(ActionEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            System.out.println("button click!");
        }
    }

    private void onButtonHoverOver(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            button.setIcon(ResourceHandler.squareIcon);
        }
    }

}