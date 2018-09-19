package main;

class ZeroExpandEvent extends Event {

    private Square s;

    ZeroExpandEvent(EventType type, Square s) {
        super(type);
        this.s = s;
    }

    Square getSquare() { return s; }

}
