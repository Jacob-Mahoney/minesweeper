package main;

import java.util.ArrayList;
import java.util.concurrent.Flow;

public class GameGrid implements Flow.Subscriber<Flow.Subscription> {

    private int width, height, numberOfMines;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height, int numberOfMines) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        init();
    }

    int getWidth() { return width; }

    int getHeight() { return height; }

    Square getSquareByNumber(int number) {
        int x = number / this.height;
        int y = number % this.height;
        return grid.get(x).get(y);
    }

    private void init() {

        for (int i = 0; i < this.height; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < this.width; j++) {
                Square s = new Square(i, i);
                s.subscribe(this);
                row.add(s);
            }
            grid.add(row);
        }

        int x;
        int y;
        int random;
        int count = 0;
        //ArrayList<Integer> random_numbers;
        int[] random_numbers = {0};
        ArrayList<ArrayList<Square>> square = new ArrayList<ArrayList<Square>>();

        for (int k = 0; k < this.numberOfMines; k++) {

            do {
                random = (int) (Math.random() * (this.height) * (this.width));
                x = random / this.height;
                y = random % this.height;
            } while (grid.get(x).get(y).hasMine());

            grid.get(x).get(y).setHasMine(true); //set mine

            if (x == 0) { //if mine is in first row
                if (y == 0) { //if mine is in top left corner
                    grid.get(x).get(y+1).increaseValue();
                    grid.get(x+1).get(y).increaseValue();
                    grid.get(x+1).get(y+1).increaseValue();
                }
                else if (y == this.width-1) { //if mine is in top right corner
                    grid.get(x).get(y-1).increaseValue();
                    grid.get(x+1).get(y).increaseValue();
                    grid.get(x+1).get(y-1).increaseValue();
                }
                else { //if mine is on top edge
                    grid.get(x).get(y-1).increaseValue();
                    grid.get(x).get(y+1).increaseValue();
                    grid.get(x+1).get(y-1).increaseValue();
                    grid.get(x+1).get(y).increaseValue();
                    grid.get(x+1).get(y+1).increaseValue();
                }
            }
            else if (x == this.height-1) { //if mine is in last row
                if (y == 0) { //if mine is in bottom left corner
                    grid.get(x).get(y+1).increaseValue();
                    grid.get(x-1).get(y).increaseValue();
                    grid.get(x-1).get(y+1).increaseValue();
                }
                else if (y == this.width-1) { //if mine is in bottom right corner
                    grid.get(x).get(y-1).increaseValue();
                    grid.get(x-1).get(y).increaseValue();
                    grid.get(x-1).get(y-1).increaseValue();
                }
                else { //if mine is on bottom edge
                    grid.get(x).get(y-1).increaseValue();
                    grid.get(x).get(y+1).increaseValue();
                    grid.get(x-1).get(y-1).increaseValue();
                    grid.get(x-1).get(y).increaseValue();
                    grid.get(x-1).get(y+1).increaseValue();
                }
            }
            else if (y == 0) { //if mine is on left side
                grid.get(x-1).get(y).increaseValue();
                grid.get(x-1).get(y+1).increaseValue();
                grid.get(x).get(y+1).increaseValue();
                grid.get(x+1).get(y).increaseValue();
                grid.get(x+1).get(y+1).increaseValue();
            }
            else if (y == this.width-1) { //if mine is on right side
                grid.get(x-1).get(y-1).increaseValue();
                grid.get(x-1).get(y).increaseValue();
                grid.get(x).get(y-1).increaseValue();
                grid.get(x+1).get(y-1).increaseValue();
                grid.get(x+1).get(y).increaseValue();
            }
            else { //if mine is in middle
                grid.get(x-1).get(y-1).increaseValue();
                grid.get(x-1).get(y).increaseValue();
                grid.get(x-1).get(y+1).increaseValue();
                grid.get(x).get(y-1).increaseValue();
                grid.get(x).get(y+1).increaseValue();
                grid.get(x+1).get(y-1).increaseValue();
                grid.get(x+1).get(y).increaseValue();
                grid.get(x+1).get(y+1).increaseValue();
            }
            //System.out.println(random);
        }

        //output();

    }

    void output() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(grid.get(i).get(j).hasMine() + "," + grid.get(i).get(j).getValue() + "  ");
            }
            System.out.print("\n\n");
        }
    }

    public void onSubscribe(Flow.Subscription subscription) {
        //System.out.println(subscription.toString());
    }

    @Override
    public void onNext(Flow.Subscription sub) {
        if (sub instanceof GameEndedEvent) {
            System.out.println("the game ended!");
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

}
