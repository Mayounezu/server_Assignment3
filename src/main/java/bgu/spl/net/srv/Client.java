package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class Client<T> {

    private final String username;
    private final String password;
    private ConnectionHandler<T> connectionHandler;
    private int connectionId;
    private final ConcurrentHashMap<Integer, String> channels;
    boolean isConnected;

    public Client(String username, String password, ConnectionHandler<T> connectionHandler, int connectionId) {
        this.username = username;
        this.password = password;
        this.connectionHandler = connectionHandler;
        this.channels = new ConcurrentHashMap<>();
        this.connectionId = connectionId;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ConnectionHandler<T> getConnectionHandler() {
        return connectionHandler;
    }

    public void setConnectionHandler(ConnectionHandler<T> connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public ConcurrentHashMap<Integer, String> getChannels() {
        return channels;
    }

    public boolean isSubscribed(int subscribeID, String channel) {
        return channels.get(subscribeID).equals(channel);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void connect(){
        isConnected = true;
    }

    public void disconnect(){
        isConnected = false;
    }

    public void setConnectionId(int connectionId){
        this.connectionId = connectionId;
    }




}
