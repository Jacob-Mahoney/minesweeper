package main;

class Event {

    private EventType type;

    Event(EventType type) {
        this.type = type;
    }

    EventType getType() { return type; }

}
