package main;

import java.util.ArrayList;
import java.util.List;
public class main {

    public static void main(String args[]) {

      /*  int arr[] = {0,0,0,0,0,0,0,0,0,0};
        int i = 0;
        while (i<10){
            int random = (int )(Math.random() * 81 + 1); //random number between 1 and 81
            arr[i] = random;
            System.out.println(arr[i]);
            i++;

        } */
        ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> firstRow = grid.get(0); //gets first row of grid
        int x = 9; // dummy variable dependent on user input for size selection (width)
        int y = 9; // dummy variable dependent on user input for size selection (height)
        int mines = 10;
        while (mines>0) {
            int random = (int )(Math.random() * (x) * (y) + 1); //random number between 1 and 81
            arr[i] = random;
            System.out.println(arr[i]);
            mines--;
        }

        /*ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
        ArrayList<Square> firstRow = squares.get(0);
        Square firstSquare = firstRow.get(0);
        Square secondSquare = squares.get(1).get(1);
        firstRow.set(5, new Square());
         */
    }


}
