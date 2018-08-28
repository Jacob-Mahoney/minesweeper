package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Square {

    private int x, y, value;
    private boolean hasMine;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
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
        System.out.println(x + " | " + y + " | " + value);
        /*Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            button.setIcon(ResourceHandler.test);
        }*/
    }

    /*private void onButtonHoverOver(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            //button.setIcon(ResourceHandler.squareIconHovered);
        }
    }

    private void onButtonHoverOut(MouseEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            //button.setIcon(ResourceHandler.squareIcon);
        }
    }*/

}
