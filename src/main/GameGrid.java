package main;

import java.util.ArrayList;

public class GameGrid extends Publisher<Event> implements Subscriber<Event> {

    private int width, height, numberOfMines, numberOfFlips;
    private boolean gridGenerated;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height, int numberOfMines) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        numberOfFlips = 0;
        gridGenerated = false;
        init();
    }

    int getWidth() { return width; }

    int getHeight() { return height; }

    public void onUpdated(Publisher<Event> pub, Event event) {
        if (event.getType() == EventType.ZERO_EXPAND) {
            SquareEvent e = (SquareEvent) event;
            zeroExpand(e.getSquare());
        } else if (event.getType() == EventType.BOMB_TRIGGERED) {
            updateSubscribers(new Event(EventType.GAME_OVER));
        } else if (event.getType() == EventType.SQUARE_FLIPPED) {
            numberOfFlips++;
            winCheck();
        } else if (event.getType() == EventType.SQUARE_LEFT_CLICK) {
            if (!gridGenerated) {
                SquareEvent e = (SquareEvent) event;
                generateGrid(e.getSquare());
            }
        } else if (event.getType() == EventType.SQUARE_DOUBLE_LEFT_CLICK) {
            SquareEvent e = (SquareEvent) event;
            doubleLeftClick(e.getSquare());
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

    private boolean safeZoneCheck(Square clickedSquare, Square mineSquare) { //checks to see if mine is in safezone
        int squarex = clickedSquare.getX();
        int squarey = clickedSquare.getY();
        ArrayList<Square> list = new ArrayList<Square>();
        if (squarex == 0) {
            if (squarey == 0) { //clicked in top left corner
                list.add(grid.get(squarex).get(squarey + 1));
                list.add(grid.get(squarex + 1).get(squarey));
                list.add(grid.get(squarex + 1).get(squarey + 1));
            } else if (squarey == this.width - 1) { //clicked in top right corner
                list.add(grid.get(squarex).get(squarey - 1));
                list.add(grid.get(squarex + 1).get(squarey));
                list.add(grid.get(squarex + 1).get(squarey - 1));
            } else { //clicked on top edge
                list.add(grid.get(squarex).get(squarey - 1));
                list.add(grid.get(squarex).get(squarey + 1));
                list.add(grid.get(squarex + 1).get(squarey - 1));
                list.add(grid.get(squarex + 1).get(squarey));
                list.add(grid.get(squarex + 1).get(squarey + 1));
            }
        } else if (squarex == this.height - 1) { //clicked in last row
            if (squarey == 0) { //clicked in bottom left corner
                list.add(grid.get(squarex).get(squarey+1));
                list.add(grid.get(squarex-1).get(squarey));
                list.add(grid.get(squarex-1).get(squarey+1));
            } else if (squarey == this.width - 1) { //clicked in bottom right corner
                list.add(grid.get(squarex).get(squarey-1));
                list.add(grid.get(squarex-1).get(squarey));
                list.add(grid.get(squarex-1).get(squarey-1));
            } else { //clicked on bottom edge
                list.add(grid.get(squarex).get(squarey-1));
                list.add(grid.get(squarex).get(squarey+1));
                list.add(grid.get(squarex-1).get(squarey-1));
                list.add(grid.get(squarex-1).get(squarey));
                list.add(grid.get(squarex-1).get(squarey+1));
            }
        } else if (squarey == 0) { //clicked on left side
            list.add(grid.get(squarex-1).get(squarey));
            list.add(grid.get(squarex-1).get(squarey+1));
            list.add(grid.get(squarex).get(squarey+1));
            list.add(grid.get(squarex+1).get(squarey));
            list.add(grid.get(squarex+1).get(squarey+1));
        } else if (squarey == this.width - 1) { //clicked on right side
            list.add(grid.get(squarex-1).get(squarey-1));
            list.add(grid.get(squarex-1).get(squarey));
            list.add(grid.get(squarex).get(squarey-1));
            list.add(grid.get(squarex+1).get(squarey-1));
            list.add(grid.get(squarex+1).get(squarey));
        } else { //clicked in middle
            list.add(grid.get(squarex-1).get(squarey-1));
            list.add(grid.get(squarex-1).get(squarey));
            list.add(grid.get(squarex-1).get(squarey+1));
            list.add(grid.get(squarex).get(squarey-1));
            list.add(grid.get(squarex).get(squarey+1));
            list.add(grid.get(squarex+1).get(squarey-1));
            list.add(grid.get(squarex+1).get(squarey));
            list.add(grid.get(squarex+1).get(squarey+1));
        }
        list.add(grid.get(squarex).get(squarey));
        for (int p = 0; p < list.size(); p++ ) {
            if (mineSquare == list.get(p)) {
                return true;
            }
        }
        return false;
    }

    private void generateGrid(Square square) {

        int x;
        int y;
        int random;

        for (int k = 0; k < this.numberOfMines; k++) {

            do {
                random = (int) (Math.random() * (this.height) * (this.width));
                x = random / this.height;
                y = random % this.height;
            } while (grid.get(x).get(y).hasMine() || safeZoneCheck(square, grid.get(x).get(y)));

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

        gridGenerated = true;

    }

    private void zeroExpand(Square square) {
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

    private void doubleLeftClick(Square square) {
        int x = square.getX();
        int y = square.getY();
        int count = 0;
        if (x == 0) { //if clicked in first row
            if (y == 0) { //if clicked in top left corner
                if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                     count++;
                }
                if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()){
                    grid.get(x).get(y+1).flipOver();
                    grid.get(x+1).get(y).flipOver();
                    grid.get(x+1).get(y+1).flipOver();
                }
            }
            else if (y == this.width-1) { //if clicked in top right corner
                if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()) {
                    grid.get(x).get(y - 1).flipOver();
                    grid.get(x + 1).get(y).flipOver();
                    grid.get(x + 1).get(y - 1).flipOver();
                }
            }
            else { //if clicked on top edge
                if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x+1).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()) {
                    grid.get(x).get(y - 1).flipOver();
                    grid.get(x).get(y + 1).flipOver();
                    grid.get(x + 1).get(y - 1).flipOver();
                    grid.get(x + 1).get(y).flipOver();
                    grid.get(x + 1).get(y + 1).flipOver();
                }
            }
        }
        else if (x == this.height-1) { //if clicked in last row
            if (y == 0) { //if clicked in bottom left corner
                if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()) {
                    grid.get(x).get(y + 1).flipOver();
                    grid.get(x - 1).get(y).flipOver();
                    grid.get(x - 1).get(y + 1).flipOver();
                }
            }
            else if (y == this.width-1) { //if clicked in bottom right corner
                if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()) {
                    grid.get(x).get(y - 1).flipOver();
                    grid.get(x - 1).get(y).flipOver();
                    grid.get(x - 1).get(y - 1).flipOver();
                }
            }
            else { //if clicked on bottom edge
                if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y-1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (grid.get(x-1).get(y+1).getState() == SquareState.FLAGGED) {
                    count++;
                }
                if (count == square.getValue()) {
                    grid.get(x).get(y - 1).flipOver();
                    grid.get(x).get(y + 1).flipOver();
                    grid.get(x - 1).get(y - 1).flipOver();
                    grid.get(x - 1).get(y).flipOver();
                    grid.get(x - 1).get(y + 1).flipOver();
                }
            }
        }
        else if (y == 0) { //if clicked on left side
            if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x-1).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (count == square.getValue()) {
                grid.get(x - 1).get(y).flipOver();
                grid.get(x - 1).get(y + 1).flipOver();
                grid.get(x).get(y + 1).flipOver();
                grid.get(x + 1).get(y).flipOver();
                grid.get(x + 1).get(y + 1).flipOver();
            }
        }
        else if (y == this.width-1) { //if clicked on right side
            if (grid.get(x-1).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (count == square.getValue()) {
                grid.get(x - 1).get(y - 1).flipOver();
                grid.get(x - 1).get(y).flipOver();
                grid.get(x).get(y - 1).flipOver();
                grid.get(x + 1).get(y - 1).flipOver();
                grid.get(x + 1).get(y).flipOver();
            }
        }
        else { //if clicked in middle
            if (grid.get(x-1).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x-1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x-1).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y-1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (grid.get(x+1).get(y+1).getState() == SquareState.FLAGGED) {
                count++;
            }
            if (count == square.getValue()) {
                grid.get(x - 1).get(y - 1).flipOver();
                grid.get(x - 1).get(y).flipOver();
                grid.get(x - 1).get(y + 1).flipOver();
                grid.get(x).get(y - 1).flipOver();
                grid.get(x).get(y + 1).flipOver();
                grid.get(x + 1).get(y - 1).flipOver();
                grid.get(x + 1).get(y).flipOver();
                grid.get(x + 1).get(y + 1).flipOver();
            }
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
        int nonMineSquares = (width*height) - numberOfMines;
        if (numberOfFlips == nonMineSquares) {
            updateSubscribers(new Event(EventType.GAME_WON));
        }
    }

}