package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// first frame that is shown on start
public class GameWindow extends JFrame {

    private JPanel grid;
    private JLabel timer;
    private GameGrid gameGrid;
    private int current = 0;

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

        GridLayout gridLayout = new GridLayout(gameGrid.getWidth(),gameGrid.getHeight());
        int numberOfSquares = gameGrid.getWidth() * gameGrid.getHeight();

        grid.setLayout(gridLayout);
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        grid.setBackground(color);

        for (int i = 0; i < numberOfSquares; i++) {

            JButton button = new JButton();
            Square square = gameGrid.getSquareByNumber(i);

            /*switch (square.getValue()) {
                case 0:
                    button.setIcon(ResourceHandler.squareIcon);
                    break;
                case 1:
                    button.setIcon(ResourceHandler.num1);
                    break;
                case 2:
                    button.setIcon(ResourceHandler.num2);
                    break;
                case 3:
                    button.setIcon(ResourceHandler.num3);
                    break;
                case 4:
                    button.setIcon(ResourceHandler.num4);
                    break;
                case 5:
                    button.setIcon(ResourceHandler.num5);
                    break;
                case 6:
                    button.setIcon(ResourceHandler.num6);
                    break;
                case 7:
                    button.setIcon(ResourceHandler.num7);
                    break;
                case 8:
                    button.setIcon(ResourceHandler.num8);
                    break;
            } */
            button.setIcon(ResourceHandler.squareIcon);
            /*if (square.hasMine()) {
                button.setIcon(ResourceHandler.bomb);
            } */

            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            button.setMinimumSize(new Dimension(24, 24));
            button.setPreferredSize(new Dimension(24, 24));
            button.setMaximumSize(new Dimension(24, 24));

            button.addActionListener(square::onButtonClick);

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

}