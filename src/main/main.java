package main;

public class main {

    public static void main(String args[]) {
        if (ResourceHandler.loadResources()) {
            Game game = new Game(9, 9, 10);
            //NewGameWindow asdf = new NewGameWindow();
        }
    }

}
