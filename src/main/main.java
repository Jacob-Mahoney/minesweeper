package main;

public class main {

    public static void main(String args[]) {
        if (ResourceHandler.loadResources()) {
            GameGrid grid = new GameGrid(9, 9, 10);
            GameWindow window = new GameWindow(grid);
        }
    }

}
