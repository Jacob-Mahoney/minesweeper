package main;

import javax.swing.*;

class WindowHandler {

    private static JFrame currentWindow = null;

    private WindowHandler() {

    }

    static void newGame(int width, int height, int mines) {
        killCurrentWindow();
        Game game = new Game(width, height, mines);
        currentWindow = game.getGameWindow();
    }

    static void newGameWindow() {
        killCurrentWindow();
        currentWindow = new NewGameWindow();
    }

    private static void killCurrentWindow() {
        if (currentWindow != null) {
            currentWindow.setVisible(false);
            currentWindow.dispose();
        }
    }

}
