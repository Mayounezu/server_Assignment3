package bgu.spl.net.impl.stomp;

import bgu.spl.net.impl.stomp.Frames.ConnectFrame;
import bgu.spl.net.srv.Connections;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Frame {

    private final int senderId;
    protected ConcurrentHashMap<String, String> headers;
    protected int connectionId;
    protected Connections<String> connections;


    protected Frame(ConcurrentHashMap headers, int connectionId, Connections<String> connections) {
        this.senderId = connectionId;
        this.headers = headers;
        this.connections = connections;
    }

    public abstract void processFrame();

    public abstract String toString();


}
