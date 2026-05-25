package observerPattern;

public interface YoutubeChannel {
    void register(Subscriber subscriber);
    void deregister(Subscriber subscriber);
    void notifySubscribers(String title);
}
