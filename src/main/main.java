package main;

import java.util.ArrayList;

public class main {

    public static void main(String args[]) {

        Window window = new Window();
        ArrayList<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
        ArrayList<Square> firstRow = squares.get(0);
        firstRow.set(5, new Square());

        Square secondSquare = squares.get(1).get(1);

        squares.get(0).set(0, new Square());

    }

}
