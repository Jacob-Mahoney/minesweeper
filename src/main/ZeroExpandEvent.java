package main;

class ZeroExpandEvent extends Event {

    private Square s;

    ZeroExpandEvent(Square s) {
        super(EventType.ZERO_EXPAND);
        this.s = s;
    }

    Square getSquare() { return s; }

}
