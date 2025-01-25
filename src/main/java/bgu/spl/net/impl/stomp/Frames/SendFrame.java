package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class SendFrame extends Frame {

    protected SendFrame(ConcurrentHashMap headers, int connectionId, Connections<String> connections) {
        super(headers, connectionId, connections);
        this.commandName = "SEND";
    }

    @Override
    public void processFrame() {

    }

    @Override
    public String toString() {
        return "";
    }
}
