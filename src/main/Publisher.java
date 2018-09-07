package main;

import java.util.ArrayList;

public abstract class Publisher {

    private ArrayList<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<Subscriber>();
    }

    void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }



}
