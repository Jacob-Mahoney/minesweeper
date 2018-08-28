package main;

public class Square {

    private int x, y, value;
    private boolean hasMine;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        hasMine = false;
        value = 0;
    }

    int getValue() { return value; }

    void increaseValue() {
        value++;
    }

    int getX() { return x; }

    int getY() { return y; }

    int getvalue() { return value;}

    boolean hasMine() { return hasMine; }

    void setHasMine(boolean hasMine) { this.hasMine = hasMine; }

}
