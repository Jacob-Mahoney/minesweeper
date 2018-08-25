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

        Color color = new Color(35, 35, 35);

        grid = new JPanel();
        timer = new JLabel("timer");

        GridLayout gridLayout = new GridLayout(9,9);

        grid.setLayout(gridLayout);
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        grid.setBackground(color);

        for (int i = 0; i < 81; i++) {

            JButton button = new JButton();

            if (i == 0) {
                button.setIcon(ResourceHandler.num1);
            } else if (i == 1) {
                button.setIcon(ResourceHandler.num2);
            } else if (i == 2) {
                button.setIcon(ResourceHandler.num3);
            } else if (i == 3) {
                button.setIcon(ResourceHandler.num4);
            } else if (i == 4) {
                button.setIcon(ResourceHandler.num5);
            } else if (i == 5) {
                button.setIcon(ResourceHandler.num6);
            } else if (i == 6) {
                button.setIcon(ResourceHandler.num7);
            } else if (i == 7) {
                button.setIcon(ResourceHandler.num8);
            } else {
                button.setIcon(ResourceHandler.squareIcon);
            }

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

        getContentPane().setBackground(color);

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
            //button.setIcon(ResourceHandler.test);
        }
    }

    private void onButtonHoverOver(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            //button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            //button.setIcon(ResourceHandler.squareIcon);
        }
    }

}