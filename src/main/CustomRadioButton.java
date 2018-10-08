package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CustomRadioButton extends JRadioButton {

    private int width, height;
    private ImageIcon iconNormal, iconHovered, iconSelected;

    CustomRadioButton(ImageIcon iconNormal, ImageIcon iconHovered, ImageIcon iconSelected, int width, int height) {
        this.iconNormal = iconNormal;
        this.iconHovered = iconHovered;
        this.iconSelected = iconSelected;
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
        setIcon(iconNormal);
        setSelectedIcon(iconSelected);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected())
                    setIcon(iconHovered);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected())
                    setIcon(iconNormal);
            }
        });

    }



}
