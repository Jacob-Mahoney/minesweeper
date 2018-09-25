package main;

public class Game implements Subscriber<Event> {

    Game(int width, int height, int numberOfMines) {

        GameGrid grid = new GameGrid(width, height, numberOfMines);
        grid.addSubscriber(this);
        GameWindow window = new GameWindow(grid);

    }

    public void onUpdated(Publisher<Event> pub, Event event) {
        if (event.getType() == EventType.GAME_WON) {
            gameWon();
        } else if (event.getType() == EventType.GAME_OVER) {
            gameOver();
        }
    }

    private void gameOver() {
        System.out.println("game over!");
    }

    private void gameWon() {
        System.out.println("game won!");
    }

}
