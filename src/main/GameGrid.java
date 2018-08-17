package main;

import java.util.ArrayList;

public class GameGrid {

    private int width, height, numberofmines;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height, int numberofmines) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        this.numberofmines = numberofmines;
        init();
    }

    private void init() {
        for (int i = 0; i < this.height; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < this.width; j++) {
                row.add(new Square(i, j, false));
            }
            grid.add(row);
        }
        int quotient;
        int remainder;
        ArrayList<ArrayList<Square>> square = new ArrayList<ArrayList<Square>>();
        for (int k = 0; k < this.numberofmines; k++) {
            int random = (int )(Math.random() * (this.height) * (this.width)); //random number between 1 and 81
            quotient = random/this.height;
            remainder = random%this.height;
            grid.get(quotient).set(remainder, new Square(quotient, remainder, true));
            System.out.println(random);
        }
    }

    void output() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(grid.get(i).get(j).getX() + "," + grid.get(i).get(j).getY() +  "," + grid.get(i).get(j).hasMine() + " ");
            }
            System.out.print("\n\n");
        }
    }

}
