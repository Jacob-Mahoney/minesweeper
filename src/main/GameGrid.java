package main;

import java.util.ArrayList;

public class GameGrid {

    private int width, height, numberOfMines;
    private ArrayList<ArrayList<Square>> grid;

    GameGrid(int width, int height, int numberOfMines) {
        grid = new ArrayList<ArrayList<Square>>();
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        init();
    }

    private void init() {
        for (int i = 0; i < this.height; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < this.width; j++) {
                row.add(new Square(i, j, false, 0));
            }
            grid.add(row);
        }
        int quotient;
        int remainder;
        int random;
        int count = 0;
        //ArrayList<Integer> random_numbers;
        int[] random_numbers = {0};
        ArrayList<ArrayList<Square>> square = new ArrayList<ArrayList<Square>>();
        for (int k = 0; k < this.numberofmines; k++) {
            do {
                random = (int) (Math.random() * (this.height) * (this.width));
                quotient = random / this.height;
                remainder = random % this.height;
            } while (grid.get(quotient).get(remainder).hasMine() == true);

            grid.get(quotient).set(remainder, new Square(quotient, remainder, true, 0));
            if (quotient == 0) {
                if (remainder == 0) {
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient+1).get(remainder).increaseValue();
                }
                else if (remainder == this.width) {
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get (quotient+1).get(remainder).increaseValue();
                }
                else {
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient+1).get(remainder-1).increaseValue();
                    grid.get(quotient+1).get(remainder).increaseValue();
                    grid.get(quotient+1).get(remainder+1).increaseValue();
                }
            }
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
