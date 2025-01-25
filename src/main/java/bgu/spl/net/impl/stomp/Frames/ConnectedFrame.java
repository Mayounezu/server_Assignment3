package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectedFrame extends Frame {

    String stompVersion;

    public ConnectedFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections connection) {
        super(headers, connectionId, connection);
        stompVersion = headers.get("accept-version");
        this.commandName = "CONNECTED";
    }

    public ConnectedFrame(int connectionId, String stompVersion) {
        super(null, connectionId, null);
        this.stompVersion = stompVersion;
    }

    @Override
    public void processFrame() {
        //should not be called
    }

    @Override
    public String toString() {
        return commandName + "\n" +
                "version:" + stompVersion + "\n" +
                "\n" + '\u0000';
    }
}
