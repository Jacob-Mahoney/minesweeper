package main;

public class main {

    public static void main(String args[]) {
        if (ResourceHandler.loadResources()) {
            GameGrid grid = new GameGrid(5, 5, 7);
            grid.output();
            GameWindow window = new GameWindow(grid);

        }

    }
}
