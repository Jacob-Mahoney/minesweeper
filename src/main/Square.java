package main;

public class Square {

    private int x, y;
    private boolean hasMine;

    Square(int x, int y, boolean hasMine) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
    }

    int getX() { return x; }

    int getY() { return y; }

    boolean hasMine() { return hasMine; }

}
