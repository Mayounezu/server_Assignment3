package bgu.spl.net.impl.stomp;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.impl.stomp.Frames.FrameParser;
import bgu.spl.net.srv.Connections;

public class stompProtocolImp implements StompMessagingProtocol<String> {
    private Connections<String> connections;
    private int connectionId;
    boolean shouldTerminate = false;


    @Override
    public void start(int connectionId, Connections<String> connections) {
        this.connections = connections;
        this.connectionId = connectionId;
    }

    @Override
    public void process(String msg) {
        Frame frame = FrameParser.ParseMessage(msg,connections, connectionId); //return the frame that the message describes
        frame.processFrame();  //process the frame and return the response
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
