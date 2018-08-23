package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ResourceHandler {

    static ImageIcon squareIcon;
    static ImageIcon squareIconHovered;
    static ImageIcon test;

    private ResourceHandler() {

    }

    static boolean loadResources() {
        try {
            squareIcon = getImageIconFromResource("../resources/square.jpg");
            squareIconHovered = getImageIconFromResource("../resources/square-hovered.jpg");
            test = getImageIconFromResource("../resources/square-8.jpg");
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
