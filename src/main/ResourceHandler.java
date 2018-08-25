package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ResourceHandler {

    static ImageIcon squareIcon;
    static ImageIcon squareIconHovered;
    static ImageIcon num1;
    static ImageIcon num2;
    static ImageIcon num3;
    static ImageIcon num4;
    static ImageIcon num5;
    static ImageIcon num6;
    static ImageIcon num7;
    static ImageIcon num8;

    private ResourceHandler() {

    }

    static boolean loadResources() {
        try {
            squareIcon = getImageIconFromResource("../resources/square.jpg");
            squareIconHovered = getImageIconFromResource("../resources/square-hovered.jpg");
            num1 = getImageIconFromResource("../resources/square-1.jpg");
            num2 = getImageIconFromResource("../resources/square-2.jpg");
            num3 = getImageIconFromResource("../resources/square-3.jpg");
            num4 = getImageIconFromResource("../resources/square-4.jpg");
            num5 = getImageIconFromResource("../resources/square-5.jpg");
            num6 = getImageIconFromResource("../resources/square-6.jpg");
            num7 = getImageIconFromResource("../resources/square-7.jpg");
            num8 = getImageIconFromResource("../resources/square-8.jpg");
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
