package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;

public class FrameHelper {

    public static final String StompVersion = "1.2";
    public static final String Host = "stomp.cs.bgu.ac.il";

    public static void sendConnected(int connectionId, Connections<String> connections, String stompVer) {
        ConnectedFrame connectedFrame = new ConnectedFrame(connectionId, stompVer);
        connections.send(connectionId, connectedFrame.toString());

    }

    public static void sendError(int connectionId, Connections<String> connections, ConcurrentHashMap<String, String> headers) {
        ErrorFrame errorFrame = new ErrorFrame(headers, connectionId, connections);
        connections.send(connectionId, errorFrame.toString());
    }

    public static void sendReceipt(int connectionId, Connections<String> connections, String recipt) {
        ConcurrentHashMap<String, String> headers = new ConcurrentHashMap<>();
        headers.put("receipt-id", recipt);
        ReceiptFrame receiptFrame = new ReceiptFrame(headers, connectionId, connections);
        connections.send(connectionId, receiptFrame.toString());
    }

}
