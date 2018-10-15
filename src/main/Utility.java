package main;

import javax.swing.*;
import java.awt.*;

public class Utility {

    private Utility() {

    }

    static <T extends JComponent> T setComponentSize(T comp, Integer width, Integer height) {

        if (width == null) {
            width = comp.getPreferredSize().width;
        }

        if (height == null) {
            height = comp.getPreferredSize().height;
        }

        comp.setSize(new Dimension(width, height));
        comp.setMinimumSize(new Dimension(width, height));
        comp.setPreferredSize(new Dimension(width, height));
        comp.setMaximumSize(new Dimension(width, height));
        return comp;

    }

}
