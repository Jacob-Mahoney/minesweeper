package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Square {

    private int x, y, value;
    private boolean hasMine, flippedOver;
    private GameGrid gameGrid;

    Square(int x, int y, GameGrid gameGrid) {
        this.x = x;
        this.y = y;
        this.gameGrid = gameGrid;
        hasMine = false;
        flippedOver = false;
        value = 0;
    }

    int getValue() { return value; }

    void increaseValue() {
        value++;
    }

    int getX() { return x; }

    int getY() { return y; }

    boolean hasMine() { return hasMine; }

    void setHasMine(boolean hasMine) { this.hasMine = hasMine; }

    void onButtonClick(ActionEvent e) {

        if (!flippedOver) {

            flippedOver = true;
            JButton button = (JButton) e.getSource();

            if (hasMine) {

                button.setIcon(ResourceHandler.bomb);

            } else {

                switch (value) {
                    case 0:
                        button.setIcon(ResourceHandler.num0);

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

    void onButtonHoverOver(MouseEvent e) {
        if (!flippedOver) {
            JButton button = (JButton) e.getSource();
            button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    void onButtonHoverOut(MouseEvent e) {
        if (!flippedOver) {
            JButton button = (JButton) e.getSource();
            button.setIcon(ResourceHandler.squareIcon);
        }
    }
    void expand() {
        //if (gameGrid.get(x).get(y+1).getValue() == 0) {

        }
    }

