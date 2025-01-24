package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.Connections;

public class FrameHelper {

    public static final String StompVersion = "1.2";
    public static final String Host = "stomp.cs.bgu.ac.il";

    public static void sendConnected(int connectionId, Connections<String> connections, String stompVer) {
        ConnectedFrame connectedFrame = new ConnectedFrame(connectionId, stompVer);
        connections.send(connectionId, connectedFrame.toString());

    }
}
