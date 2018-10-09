package main;

class SquareEvent extends Event {

    private Square s;

    SquareEvent(EventType type, Square s) {
        super(type);
        this.s = s;
    }

    Square getSquare() { return s; }

}
