package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square {

    private int x, y, value;
    private boolean hasMine;
    private GameGrid gameGrid;
    private JButton button;
    private squareState state;

    Square(int x, int y, GameGrid gameGrid) {
        this.x = x;
        this.y = y;
        this.gameGrid = gameGrid;
        hasMine = false;
        value = 0;
        button = new JButton();
        initButton();
        state = squareState.NOT_FLIPPED_OVER;
    }

    private void initButton() {

        button.setIcon(ResourceHandler.squareIcon);

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setMinimumSize(new Dimension(24, 24));
        button.setPreferredSize(new Dimension(24, 24));
        button.setMaximumSize(new Dimension(24, 24));

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

    }

    JButton getButton() {
        return button;
    }

    int getValue() {
        return value;
    }

    void increaseValue() {
        value++;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean hasMine() {
        return hasMine;
    }

    void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    private void onButtonClick(ActionEvent e) {
            flipOver();
            }


    private void onButtonHoverOver(MouseEvent e) {
        if (state == squareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut(MouseEvent e) {
        if (state == squareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIcon);
        }
    }

    void flipOver() {
        if (state == squareState.NOT_FLIPPED_OVER) {
            state = squareState.FLIPPED_OVER;

            if (hasMine) {

                button.setIcon(ResourceHandler.bomb);
                gameGrid.endOfGame();


            } else {

                switch (value) {
                    case 0:
                        button.setIcon(ResourceHandler.num0);
                        gameGrid.expand(this);
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
                }

            }
        }
    }
}