package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class FrameParser {

    public FrameParser() {
        //do nothing.
    }

    public static Frame ParseMessage(String message, Connections<String> connections, int connectionID){
        String[] headersArray = message.split("\n");
        Queue<String> headersQueue = new LinkedList<>(Arrays.asList(headersArray));
        ConcurrentHashMap<String, String> headers = getHeadersFromQueue(headersQueue);

        String command = headersQueue.remove();
        return getFrameByCommand(command, headers, connections, connectionID);

    }

    private static Frame getFrameByCommand(String command, ConcurrentHashMap<String, String> headers, Connections<String> connections, int connectionID) {
        switch (command) {
            case "CONNECT":
                return new ConnectFrame(headers, connectionID, connections);
            case "SEND":
                return new SendFrame(headers, connectionID, connections);
            case "SUBSCRIBE":
                return new SubscribeFrame(headers, connectionID, connections);
            case "UNSUBSCRIBE":
                return new UnsubscribeFrame(headers, connectionID, connections);
            case "DISCONNECT":
                return new DisconnectFrame(headers, connectionID, connections);
            case "MESSAGE":
                return new MessageFrame(headers, connectionID, connections);
            case "CONNECTED":
                return new ConnectedFrame(headers, connectionID, connections);
            case "RECEIPT":
                return new ReceiptFrame(headers, connectionID, connections);
            case "ERROR":
                return new ErrorFrame(headers, connectionID, connections);
            default:
                return null;

        }
    }

    public static ConcurrentHashMap<String, String> getHeadersFromQueue(Queue<String> headersQueue){
        ConcurrentHashMap<String, String> headers = new ConcurrentHashMap<>();
        while(!headersQueue.isEmpty() && !headersQueue.peek().equals("")){
            String header[] = headersQueue.remove().split(":"); //spliting based on ":"
            headers.put(header[0], header[1]); //first value is the name of the key, second value is the value of the key
        }
        return headers;
    }

}
