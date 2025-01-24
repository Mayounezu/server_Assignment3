package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiptFrame extends Frame {


    protected ReceiptFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connection) {
        super(headers, connectionId, connection);
    }

    @Override
    public void processFrame() {

    }

    @Override
    public String toString() {
        return "";
    }
}
