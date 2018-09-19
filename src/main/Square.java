package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square extends Publisher<Event> {

    private int x, y, value;
    private boolean hasMine;
    private JButton button;
    private SquareState state;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
        value = 0;
        button = new JButton();
        initButton();
        state = SquareState.NOT_FLIPPED_OVER;
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
                onButtonHoverOver();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                onButtonHoverOut();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    onRightClick();
                }
            }
        });

        button.addActionListener((ActionEvent e) -> onLeftClick());

    }

    JButton getButton() {
        return button;
    }

    SquareState getState() {
        return state;
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

    void flipOver() {

        if (state == SquareState.NOT_FLIPPED_OVER) {

            state = SquareState.FLIPPED_OVER;

            if (hasMine) {

                button.setIcon(ResourceHandler.bomb);
                updateSubscribers(new Event(EventType.GAME_ENDED));

            } else {

                switch (value) {
                    case 0:
                        button.setIcon(ResourceHandler.num0);
                        updateSubscribers(new ZeroExpandEvent(EventType.ZERO_EXPAND, this));
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

    private void onLeftClick() {
        flipOver();
    }

    private void onRightClick() {

        if (state == SquareState.NOT_FLIPPED_OVER) {
            //set icon to the flag icon
        }

        if (state == SquareState.FLAGGED) {
            button.setIcon(ResourceHandler.squareIcon);
        }
    }

    private void onButtonHoverOver() {
        if (state == SquareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut() {
        if (state == SquareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIcon);
        }
    }

}