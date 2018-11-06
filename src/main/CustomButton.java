package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CustomButton extends JButton {

    private int width, height;
    private ImageIcon icon;
    private ImageIcon hoverIcon;

    CustomButton(ImageIcon icon, int width, int height) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.hoverIcon = null;
        init();
    }

    void setHoverIcon(ImageIcon hoverIcon) {
        this.hoverIcon = hoverIcon;
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverOver();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                onHoverOut();
            }
        });

    }

    private void onHoverOver() {
        if (this.hoverIcon != null) {
            setIcon(hoverIcon);
        }
    }

    private void onHoverOut() {
        if (this.hoverIcon != null) {
            setIcon(icon);
        }
    }

}
