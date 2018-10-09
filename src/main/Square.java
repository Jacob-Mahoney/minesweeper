package main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square extends Publisher<Event> {

    private int x, y, value;
    private boolean hasMine;
    private CustomButton button;
    private SquareState state;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
        value = 0;
        state = SquareState.NOT_FLIPPED_OVER;
        initButton();
    }

    private void initButton() {

        button = new CustomButton(ResourceHandler.squareIcon, 24, 24);

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
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() < 2) {
                        onLeftClick();
                    } else {
                        onDoubleLeftClick();
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    onRightClick();
                }
            }
        });

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
                updateSubscribers(new Event(EventType.BOMB_TRIGGERED));

            } else {

                updateSubscribers(new Event(EventType.SQUARE_FLIPPED));

                switch (value) {
                    case 0:
                        button.setIcon(ResourceHandler.num0);
                        updateSubscribers(new SquareEvent(EventType.ZERO_EXPAND, this));
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
        updateSubscribers(new SquareEvent(EventType.SQUARE_LEFT_CLICK, this));
        flipOver();
    }

    private void onDoubleLeftClick() {
        if (state == SquareState.NOT_FLIPPED_OVER) {
            //
        }
        //updateSubscribers(new SquareEvent(EventType.SQUARE_DOUBLE_LEFT_CLICK, this));
    }

    private void onRightClick() {
        if (state == SquareState.FLAGGED) {
            state = SquareState.NOT_FLIPPED_OVER;
            button.setIcon(ResourceHandler.squareIcon);
        }
        else if (state == SquareState.NOT_FLIPPED_OVER) {
            state = SquareState.FLAGGED;
            button.setIcon(ResourceHandler.flag);
        }
    }

    private void onButtonHoverOver() {
        if (state == SquareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIconHovered);
        }
        if (state == SquareState.FLAGGED) {
            button.setIcon(ResourceHandler.flagHovered);
        }
    }

    private void onButtonHoverOut() {
        if (state == SquareState.NOT_FLIPPED_OVER) {
            button.setIcon(ResourceHandler.squareIcon);
        }
        if (state == SquareState.FLAGGED) {
            button.setIcon(ResourceHandler.flag);
        }
    }

}