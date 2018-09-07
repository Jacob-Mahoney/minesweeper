package main;

import java.util.concurrent.Flow;

public class GameEndedEvent implements Flow.Subscription {

    private Flow.Subscriber subscriber;

    public GameEndedEvent(Flow.Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        subscriber.onNext(this);
        subscriber.onComplete();
    }

    @Override
    public void cancel() {

    }

}
