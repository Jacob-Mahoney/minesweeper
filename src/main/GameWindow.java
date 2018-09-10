package main;

import javax.swing.*;
import java.awt.*;

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

        GridLayout gridLayout = new GridLayout(gameGrid.getWidth(), gameGrid.getHeight());
        int numberOfSquares = gameGrid.getWidth() * gameGrid.getHeight();

        grid.setLayout(gridLayout);
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        grid.setBackground(color);

        for (int i = 0; i < numberOfSquares; i++) {

            Square square = gameGrid.getSquareByNumber(i);
            JButton button = square.getButton();

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