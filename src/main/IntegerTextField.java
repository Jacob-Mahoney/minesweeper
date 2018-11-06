package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class IntegerTextField extends JTextField {

    private int width, height, minValue, maxValue;
    private String previousText;

    IntegerTextField(int width, int height, int minValue, int maxValue) {
        this.width = width;
        this.height = height;
        this.minValue = minValue;
        this.maxValue = maxValue;
        init();
    }

    private void init() {

        setBorder(null);
        setSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        setBackground(new Color(60, 57, 64));
        setForeground(Color.WHITE);
        setCaretColor(Color.WHITE);
        setHorizontalAlignment(SwingConstants.CENTER);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                previousText = getText();
            }
            @Override
            public void focusLost(FocusEvent e) {
                String text = getText();
                if (!Utility.isInt(text)) {
                    setText(previousText);
                } else {
                    int num = Integer.parseInt(text);
                    if (num < minValue || num > maxValue) {
                        setText(previousText);
                    }
                }
            }
        });

    }

}
