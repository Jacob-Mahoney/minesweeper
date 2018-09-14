package main;

public interface Subscriber<T> {

    void onUpdated(Publisher<T> pub, T arg);

}
