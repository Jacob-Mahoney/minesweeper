package main;

class SquareLeftClickEvent extends Event {

    private Square s;

    SquareLeftClickEvent(Square s) {
        super(EventType.SQUARE_LEFT_CLICK);
        this.s = s;
    }

    Square getSquare() { return s; }

}
