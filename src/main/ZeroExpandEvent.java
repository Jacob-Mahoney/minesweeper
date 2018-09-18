package main;

public class ZeroExpandEvent implements Event {

    private Square s;

    ZeroExpandEvent(Square s) {
        this.s = s;
    }

    Square getSquare() { return s; }

}
