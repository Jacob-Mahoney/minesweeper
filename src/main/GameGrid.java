package main;

import java.util.ArrayList;

public class GameGrid implements Subscriber<Event> {

    private int width, height, numberOfMines, numberOfFlips;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height, int numberOfMines) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        numberOfFlips = 0;
        init();
    }

    int getWidth() { return width; }

    int getHeight() { return height; }

    public void onUpdated(Publisher<Event> pub, Event event) {
        if (event.getType() == EventType.ZERO_EXPAND) {
            ZeroExpandEvent e = (ZeroExpandEvent) event;
            expand(e.getSquare());
        } else if (event.getType() == EventType.BOMB_TRIGGERED) {
            endOfGame();
        } else if (event.getType() == EventType.SQUARE_FLIPPED) {
            numberOfFlips++;
            winCheck();
        }
    }

    Square getSquareByNumber(int number) {
        int x = number / this.height;
        int y = number % this.height;
        return grid.get(x).get(y);
    }

    private void init() {

        for (int i = 0; i < this.height; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < this.width; j++) {
                Square s = new Square(i, j);
                s.addSubscriber(this);
                row.add(s);
            }
            grid.add(row);
        }
    }

    private void generateGrid(Square square) {
        int x;
        int y;
        int random;
        int count = 0;
        //ArrayList<Integer> random_numbers;
        int[] random_numbers = {0};
        ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();

        for (int k = 0; k < this.numberOfMines; k++) {

            do {
                random = (int) (Math.random() * (this.height) * (this.width));
                x = random / this.height;
                y = random % this.height;
            } while (grid.get(x).get(y).hasMine());

            grid.get(x).get(y).setHasMine(true); //set mine

            if (x == 0) { //if mine is in first row
                if (y == 0) { //if mine is in top left corner
                    grid.get(x).get(y + 1).increaseValue();
                    grid.get(x + 1).get(y).increaseValue();
                    grid.get(x + 1).get(y + 1).increaseValue();
                } else if (y == this.width - 1) { //if mine is in top right corner
                    grid.get(x).get(y - 1).increaseValue();
                    grid.get(x + 1).get(y).increaseValue();
                    grid.get(x + 1).get(y - 1).increaseValue();
                } else { //if mine is on top edge
                    grid.get(x).get(y - 1).increaseValue();
                    grid.get(x).get(y + 1).increaseValue();
                    grid.get(x + 1).get(y - 1).increaseValue();
                    grid.get(x + 1).get(y).increaseValue();
                    grid.get(x + 1).get(y + 1).increaseValue();
                }
            } else if (x == this.height - 1) { //if mine is in last row
                if (y == 0) { //if mine is in bottom left corner
                    grid.get(x).get(y + 1).increaseValue();
                    grid.get(x - 1).get(y).increaseValue();
                    grid.get(x - 1).get(y + 1).increaseValue();
                } else if (y == this.width - 1) { //if mine is in bottom right corner
                    grid.get(x).get(y - 1).increaseValue();
                    grid.get(x - 1).get(y).increaseValue();
                    grid.get(x - 1).get(y - 1).increaseValue();
                } else { //if mine is on bottom edge
                    grid.get(x).get(y - 1).increaseValue();
                    grid.get(x).get(y + 1).increaseValue();
                    grid.get(x - 1).get(y - 1).increaseValue();
                    grid.get(x - 1).get(y).increaseValue();
                    grid.get(x - 1).get(y + 1).increaseValue();
                }
            } else if (y == 0) { //if mine is on left side
                grid.get(x - 1).get(y).increaseValue();
                grid.get(x - 1).get(y + 1).increaseValue();
                grid.get(x).get(y + 1).increaseValue();
                grid.get(x + 1).get(y).increaseValue();
                grid.get(x + 1).get(y + 1).increaseValue();
            } else if (y == this.width - 1) { //if mine is on right side
                grid.get(x - 1).get(y - 1).increaseValue();
                grid.get(x - 1).get(y).increaseValue();
                grid.get(x).get(y - 1).increaseValue();
                grid.get(x + 1).get(y - 1).increaseValue();
                grid.get(x + 1).get(y).increaseValue();
            } else { //if mine is in middle
                grid.get(x - 1).get(y - 1).increaseValue();
                grid.get(x - 1).get(y).increaseValue();
                grid.get(x - 1).get(y + 1).increaseValue();
                grid.get(x).get(y - 1).increaseValue();
                grid.get(x).get(y + 1).increaseValue();
                grid.get(x + 1).get(y - 1).increaseValue();
                grid.get(x + 1).get(y).increaseValue();
                grid.get(x + 1).get(y + 1).increaseValue();
            }

        }
    }


    private void expand(Square square) {
        int x = square.getX();
        int y = square.getY();
        if (x == 0) { //if clicked in first row
            if (y == 0) { //if clicked in top left corner
                grid.get(x).get(y+1).flipOver();
                grid.get(x+1).get(y).flipOver();
                grid.get(x+1).get(y+1).flipOver();
            }
            else if (y == this.width-1) { //if clicked in top right corner
                grid.get(x).get(y-1).flipOver();
                grid.get(x+1).get(y).flipOver();
                grid.get(x+1).get(y-1).flipOver();
            }
            else { //if clicked on top edge
                grid.get(x).get(y-1).flipOver();
                grid.get(x).get(y+1).flipOver();
                grid.get(x+1).get(y-1).flipOver();
                grid.get(x+1).get(y).flipOver();
                grid.get(x+1).get(y+1).flipOver();
            }
        }
        else if (x == this.height-1) { //if clicked in last row
            if (y == 0) { //if clicked in bottom left corner
                grid.get(x).get(y+1).flipOver();
                grid.get(x-1).get(y).flipOver();
                grid.get(x-1).get(y+1).flipOver();
            }
            else if (y == this.width-1) { //if clicked in bottom right corner
                grid.get(x).get(y-1).flipOver();
                grid.get(x-1).get(y).flipOver();
                grid.get(x-1).get(y-1).flipOver();
            }
            else { //if clicked on bottom edge
                grid.get(x).get(y-1).flipOver();
                grid.get(x).get(y+1).flipOver();
                grid.get(x-1).get(y-1).flipOver();
                grid.get(x-1).get(y).flipOver();
                grid.get(x-1).get(y+1).flipOver();
            }
        }
        else if (y == 0) { //if clicked on left side
            grid.get(x-1).get(y).flipOver();
            grid.get(x-1).get(y+1).flipOver();
            grid.get(x).get(y+1).flipOver();
            grid.get(x+1).get(y).flipOver();
            grid.get(x+1).get(y+1).flipOver();
        }
        else if (y == this.width-1) { //if clicked on right side
            grid.get(x-1).get(y-1).flipOver();
            grid.get(x-1).get(y).flipOver();
            grid.get(x).get(y-1).flipOver();
            grid.get(x+1).get(y-1).flipOver();
            grid.get(x+1).get(y).flipOver();
        }
        else { //if clicked in middle
            grid.get(x-1).get(y-1).flipOver();
            grid.get(x-1).get(y).flipOver();
            grid.get(x-1).get(y+1).flipOver();
            grid.get(x).get(y-1).flipOver();
            grid.get(x).get(y+1).flipOver();
            grid.get(x+1).get(y-1).flipOver();
            grid.get(x+1).get(y).flipOver();
            grid.get(x+1).get(y+1).flipOver();
        }
    }

    private void endOfGame() {
		for (int m = 0; m < this.height; m++) {
			for (int n = 0; n < this.width; n++) {
			    Square s = grid.get(m).get(n);
				if (s.hasMine()) {
					if (s.getState() == SquareState.FLAGGED) {

                    }
                    else {
                        s.getButton().setIcon(ResourceHandler.bomb);
                    }
				}
			}
		}
	}

	private void winCheck() {
        int nonMineSquares = (width*height)-numberOfMines;
        if (numberOfFlips == nonMineSquares) {
            won();
        }
    }

    private void won() {
        System.out.println("You won!");
    }

}
