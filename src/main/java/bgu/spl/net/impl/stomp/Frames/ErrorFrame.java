package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class ErrorFrame extends Frame {


    protected ErrorFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connections) {
        super(headers, connectionId, connections);
        this.commandName = "ERROR";
    }

    @Override
    public void processFrame() {
        //do nothing.
    }

    @Override
    public String toString() {
        String output = commandName + "\n";
        for (String key : headers.keySet()) {
            output += key + ":" + headers.get(key) + "\n";
        }
        output += "\u0000";
        return output;
    }
}
