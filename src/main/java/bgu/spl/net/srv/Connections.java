package bgu.spl.net.srv;

import java.io.IOException;

public interface Connections<T> {


    boolean subscribe(int connectionId, int subscribeID, String channel);

    boolean unsubscribe(int connectionId, int subscribeID, String channel);

    boolean isSubscribe(int connectionId, String channel);

    boolean isChannel(String channel);

    boolean isUserInfoValid(String username, String password);
    boolean isUserLoggedIn(String username);

    boolean send(int connectionId, T msg);

    void login(int connectionId, String username, String password);

    void send(String channel, T msg);

    void disconnect(int connectionId);

    Client<T> getClient(int connectionId);
}
