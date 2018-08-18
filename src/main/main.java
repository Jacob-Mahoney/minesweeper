package main;

import java.util.ArrayList;

public class main {

    public static void main(String args[]) {

        Window window = new Window(new GameGrid(9, 9, 10));

        //GameGrid grid = new GameGrid(9, 9, 10);
        //grid.output();
        //Square blah = grid.getSquareByLocation(4, 5);
        //System.out.println(blah.getX() + "," + blah.getY());

        /*ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
        ArrayList<Integer> firstRow = new ArrayList<Integer>();
        int x = 9; // dummy variable dependent on user input for size selection (width)
        int y = 9; // dummy variable dependent on user input for size selection (height)
        int z;
        int remainder;
        int quotient;
        int mines = 10;
        while (mines>0) {
            int random = (int)(Math.random() * (x) * (y) + 1); //random number between 1 and 81
            quotient = random/x;
            remainder = random%x;
            //System.out.println(random + " " + quotient + " " + remainder);
            squares.get(quotient).set(remainder, new Square(remainder, quotient, true));
            //square.set(z, new Square(x, true));
            //firstRow.add(random);
            //System.out.println(firstRow);
            mines--;
        }*/

        /*for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                squares.get(row).set(col, new Square(col, row, true));
            }
        }*/

    }

}