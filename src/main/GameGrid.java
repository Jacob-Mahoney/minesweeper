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
                row.add(new Square(i, j));
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
        for (int k = 0; k < this.numberOfMines; k++) {
            do {
                random = (int) (Math.random() * (this.height) * (this.width));
                quotient = random / this.height;
                remainder = random % this.height;
            } while (grid.get(quotient).get(remainder).hasMine() == true);

            grid.get(quotient).get(remainder).setHasMine(true); //set mine

            if (quotient == 0) { //if mine is in first row
                if (remainder == 0) { //if mine is in top left corner
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient+1).get(remainder).increaseValue();
                    grid.get(quotient+1).get(remainder+1).increaseValue();
                }
                else if (remainder == this.width-1) { //if mine is in top right corner
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get(quotient+1).get(remainder).increaseValue();
                    grid.get(quotient+1).get(remainder-1).increaseValue();
                }
                else { //if mine is on top edge
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient+1).get(remainder-1).increaseValue();
                    grid.get(quotient+1).get(remainder).increaseValue();
                    grid.get(quotient+1).get(remainder+1).increaseValue();
                }
            }
            else if (quotient == this.height-1) { //if mine is in last row
                if (remainder == 0) { //if mine is in bottom left corner
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient-1).get(remainder).increaseValue();
                    grid.get(quotient-1).get(remainder+1).increaseValue();
                }
                else if (remainder == this.width-1) { //if mine is in bottom right corner
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get(quotient-1).get(remainder).increaseValue();
                    grid.get(quotient-1).get(remainder-1).increaseValue();
                }
                else { //if mine is on bottom edge
                    grid.get(quotient).get(remainder-1).increaseValue();
                    grid.get(quotient).get(remainder+1).increaseValue();
                    grid.get(quotient-1).get(remainder-1).increaseValue();
                    grid.get(quotient-1).get(remainder).increaseValue();
                    grid.get(quotient-1).get(remainder+1).increaseValue();
                }
            }
            else if (remainder == 0) { //if mine is on left side
                grid.get(quotient-1).get(remainder).increaseValue();
                grid.get(quotient-1).get(remainder+1).increaseValue();
                grid.get(quotient).get(remainder+1).increaseValue();
                grid.get(quotient+1).get(remainder).increaseValue();
                grid.get(quotient+1).get(remainder+1).increaseValue();
            }
            else if (remainder == this.width-1) { //if mine is on right side
                grid.get(quotient-1).get(remainder-1).increaseValue();
                grid.get(quotient-1).get(remainder).increaseValue();
                grid.get(quotient).get(remainder-1).increaseValue();
                grid.get(quotient+1).get(remainder-1).increaseValue();
                grid.get(quotient+1).get(remainder).increaseValue();
            }
            else { //if mine is in middle
                grid.get(quotient-1).get(remainder-1).increaseValue();
                grid.get(quotient-1).get(remainder).increaseValue();
                grid.get(quotient-1).get(remainder+1).increaseValue();
                grid.get(quotient).get(remainder-1).increaseValue();
                grid.get(quotient).get(remainder+1).increaseValue();
                grid.get(quotient+1).get(remainder-1).increaseValue();
                grid.get(quotient+1).get(remainder).increaseValue();
                grid.get(quotient+1).get(remainder+1).increaseValue();
            }
            System.out.println(random);
        }

    }

    void output() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(grid.get(i).get(j).getX() + "," + grid.get(i).get(j).getY() +  "," + grid.get(i).get(j).hasMine() + "," + grid.get(i).get(j).getvalue() + " ");
            }
            System.out.print("\n\n");
        }
    }

}
