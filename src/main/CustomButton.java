package main;

import javax.swing.*;
import java.awt.*;

class CustomButton extends JButton {

    private int width, height;
    private ImageIcon icon;

    CustomButton(ImageIcon icon, int width, int height) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setBorder(null);
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setIcon(icon);

    }

}
