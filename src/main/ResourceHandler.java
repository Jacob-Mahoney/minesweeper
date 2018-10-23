package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class ResourceHandler {

    static ImageIcon squareIcon, squareIconHovered, bomb, num0, num1, num2, num3, num4, num5, num6, num7, num8, flag, flagHovered;
    static ImageIcon beginner, intermediate, advanced;
    static ImageIcon beginnerH, intermediateH, advancedH;
    static ImageIcon beginnerI, intermediateI, advancedI;
    static ImageIcon start, startH;
    static ImageIcon xButton, xButtonHover;

    private ResourceHandler() {

    }

    static boolean loadResources() {
        try {
            squareIcon = getImageIconFromResource("../resources/square.jpg");
            squareIconHovered = getImageIconFromResource("../resources/square-hovered.jpg");
            bomb = getImageIconFromResource("../resources/bomb.jpg");
            num0 = getImageIconFromResource("../resources/square-0.jpg");
            num1 = getImageIconFromResource("../resources/square-1.jpg");
            num2 = getImageIconFromResource("../resources/square-2.jpg");
            num3 = getImageIconFromResource("../resources/square-3.jpg");
            num4 = getImageIconFromResource("../resources/square-4.jpg");
            num5 = getImageIconFromResource("../resources/square-5.jpg");
            num6 = getImageIconFromResource("../resources/square-6.jpg");
            num7 = getImageIconFromResource("../resources/square-7.jpg");
            num8 = getImageIconFromResource("../resources/square-8.jpg");
            flag = getImageIconFromResource("../resources/flag.jpg");
            flagHovered = getImageIconFromResource("../resources/flag-hovered.jpg");
            beginner = getImageIconFromResource("../resources/beg.jpg");
            intermediate = getImageIconFromResource("../resources/int.jpg");
            advanced = getImageIconFromResource("../resources/adv.jpg");
            beginnerH = getImageIconFromResource("../resources/beg h.jpg");
            intermediateH = getImageIconFromResource("../resources/int h.jpg");
            advancedH = getImageIconFromResource("../resources/adv h.jpg");
            beginnerI = getImageIconFromResource("../resources/beg i.jpg");
            intermediateI = getImageIconFromResource("../resources/int i.jpg");
            advancedI = getImageIconFromResource("../resources/adv i.jpg");
            start = getImageIconFromResource("../resources/start.jpg");
            startH = getImageIconFromResource("../resources/start h.jpg");
            xButton = getImageIconFromResource("../resources/x-button.png");
            xButtonHover = getImageIconFromResource("../resources/x-button-hover.png");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static ImageIcon getImageIconFromResource(String resource) throws IOException {
        Image image = ImageIO.read(ResourceHandler.class.getResource(resource));
        return new ImageIcon(image);
    }

}
