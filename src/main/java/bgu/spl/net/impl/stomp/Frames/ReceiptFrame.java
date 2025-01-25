package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiptFrame extends Frame {

    String recipt;

    protected ReceiptFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connection) {
        super(headers, connectionId, connection);
        recipt = headers.get("receipt-id");
    }



    @Override
    public void processFrame() {

    }

    @Override
    public String toString() {
        return "RECEIPT\n" +
                "receipt-id:" + recipt + "\n" +
                "\u0000";
    }
}
