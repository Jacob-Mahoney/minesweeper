package main;

import java.util.ArrayList;

public class GameGrid {

    private int width, height;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {
        for (int i = 0; i < this.height; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < this.width; j++) {
                row.add(new Square(j, i, false));
            }
            grid.add(row);
        }
    }

    Square getSquareByLocation(int x, int y) {
        return grid.get(y).get(x);
    }

    void setSquareByLocation(int x, int y, Square square) {
        grid.get(y).set(x, square);
    }

    void output() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(grid.get(j).get(i).getX() + "," + grid.get(j).get(i).getY() + "  ");
            }
            System.out.print("\n\n");
        }
    }

}
