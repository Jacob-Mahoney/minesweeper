package main;

public class Game implements Subscriber<Event> {

    private GameWindow gameWindow;

    Game(int width, int height, int numberOfMines) {
        GameGrid grid = new GameGrid(width, height, numberOfMines);
        grid.addSubscriber(this);
        gameWindow = new GameWindow(grid);
    }

    GameWindow getGameWindow() { return gameWindow; }

    public void onUpdated(Publisher<Event> pub, Event event) {
        if (event.getType() == EventType.GAME_WON) {
            gameWon();
        } else if (event.getType() == EventType.GAME_OVER) {
            gameOver();
        }
    }

    private void gameOver() {
        WindowHandler.newGameWindow();
        System.out.println("game over!");
    }

    private void gameWon() {
        WindowHandler.newGameWindow();
        System.out.println("game won!");
    }

}
