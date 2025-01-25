package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageFrame extends Frame {

    private static AtomicInteger messageIdNum = new AtomicInteger(0);

    int id;
    String destination;
    String body;
    String messageId;

    protected MessageFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connections, String body) {
        super(headers, connectionId, connections);
        this.commandName = "MESSAGE";

        this.body = body;
        this.destination = headers.get("destination");
        messageId = "message-" + messageIdNum.getAndIncrement();


    }


    @Override
    public void processFrame() {

    }

    @Override
    public String toString() {
        return commandName + "\n" +
                "destination:" + destination + "\n" +
                "message-id:" + messageId + "\n" +
                "\n" +
                body + "\n" + '\u0000';
    }
}
