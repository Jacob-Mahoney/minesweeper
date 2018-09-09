package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square {

    private int x, y, value;
    private boolean hasMine, flippedOver;
    private JButton button;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
        flippedOver = false;
        value = 0;
    }

    JButton initButton() {

        button = new JButton();

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

        return button;

    }

    int getValue() { return value; }

    void increaseValue() {
        value++;
    }

    int getX() { return x; }

    int getY() { return y; }

    boolean hasMine() { return hasMine; }

    void setHasMine(boolean hasMine) { this.hasMine = hasMine; }

    private void onButtonClick(ActionEvent e) {

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

    private void onButtonHoverOver(MouseEvent e) {
        if (!flippedOver) {
            JButton button = (JButton) e.getSource();
            button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut(MouseEvent e) {
        if (!flippedOver) {
            JButton button = (JButton) e.getSource();
            button.setIcon(ResourceHandler.squareIcon);
        }
    }

}
