package main;

import java.util.ArrayList;

abstract class Publisher<T> {

    private ArrayList<Subscriber<T>> subscribers;

    Publisher() {
        subscribers = new ArrayList<Subscriber<T>>();
    }

    void addSubscriber(Subscriber<T> sub) {
        subscribers.add(sub);
    }

    void updateSubscribers(T arg) {
        for (Subscriber<T> sub : subscribers) {
            sub.onUpdated(this, arg);
        }
    }

}
