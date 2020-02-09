package knut;

public interface Observer {
    void update();
    void subscribe(Subject s);
    void unsubscribe(Subject s);
}
