package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.Flow;

public class Square implements Flow.Publisher {

    private int x, y, value;
    private boolean hasMine, flippedOver;
    private GameEndedEvent subscription;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
        flippedOver = false;
        value = 0;
    }

    public void subscribe(Flow.Subscriber subscriber) {
        subscription = new GameEndedEvent(subscriber);
        subscriber.onSubscribe(subscription);
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
                if (subscription != null) {
                    System.out.println("not null");
                    subscription.request(0);
                } else {
                    System.out.println("null");
                }

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

}
