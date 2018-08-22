package main;

public class Square {

    private int x, y, value;
    private boolean hasMine;

    Square(int x, int y, boolean hasMine, int value) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
        this.value = value;
    }

    int getValue() { return value; }

    void increaseValue() {
        value++;
    }

    int getX() { return x; }

    int getY() { return y; }

    boolean hasMine() { return hasMine; }

    void setHasMine(boolean hasMine) { this.hasMine = hasMine; }

}
