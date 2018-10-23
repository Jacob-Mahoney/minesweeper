package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

class GameWindow extends BaseWindowFrame {

    private GameGrid gameGrid;

    GameWindow(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
        renderFrame();
    }

    JPanel mainContent() {

        Color color = new Color(35, 35, 35);

        JPanel grid = new JPanel();

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

        JLabel timer = new JLabel("0:00");
        timer.setForeground(Color.WHITE);
        timer = Utility.setComponentSize(timer, grid.getPreferredSize().width, null);
        timer.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel mainContent = new JPanel();
        GroupLayout layout = new GroupLayout(mainContent);
        mainContent.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(10)
            .addGroup(layout.createParallelGroup()
                .addComponent(timer)
                .addComponent(grid))
            .addGap(10)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(10)
            .addComponent(timer)
            .addGap(10)
            .addComponent(grid)
            .addGap(10)
        );

        return mainContent;

    }

}