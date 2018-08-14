package main;

import java.util.ArrayList;

public class main {

    public static void main(String args[]) {

        //ArrayList<Integer> firstRow = grid.get(0); //gets first row of grid
        //Window window = new Window();
        ArrayList<ArrayList<Square>> square = new ArrayList<ArrayList<Square>>();
        ArrayList<Integer> firstRow = new ArrayList<Integer>();
        int x = 9; // dummy variable dependent on user input for size selection (width)
        int y = 9; // dummy variable dependent on user input for size selection (height)
        int remainder;
        int quotient;
        int mines = 10;
        for (int i = 0; i < mines; i++)
        {
            int random = (int )(Math.random() * (x) * (y) + 1); //random number between 1 and 81
            quotient = random/x;
            remainder = random%x;
            //System.out.println(random + " " + quotient + " " + remainder);
            square.get(quotient).set(remainder, new Square(remainder, quotient, true));
            //square.set(z, new Square(x, true));
            //firstRow.add(random);
            //System.out.println(firstRow);
        }

        /*ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
        ArrayList<Square> firstRow = squares.get(0);
        Square firstSquare = firstRow.get(0);
        Square secondSquare = squares.get(1).get(1);
        firstRow.set(5, new Square()); */
    }


}
