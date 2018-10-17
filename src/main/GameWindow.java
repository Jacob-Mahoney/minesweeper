package main;

import javax.swing.*;
import java.awt.*;

class GameWindow extends BaseWindowFrame {

    private GameGrid gameGrid;

    GameWindow(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        renderFrame();
    }

    JPanel mainContent() {

        Color color = new Color(35, 35, 35);

        JPanel grid = new JPanel();
        JLabel timer = new JLabel("timer");
        timer.setForeground(Color.WHITE);

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

        JPanel mainContent = new JPanel();
        GroupLayout layout = new GroupLayout(mainContent);
        mainContent.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGap(25)
                .addGroup(layout.createParallelGroup()
                    .addComponent(timer)
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

        return mainContent;

    }

}