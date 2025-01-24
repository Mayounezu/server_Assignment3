package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;

import java.util.HashMap;

public class SubscribeFrame extends Frame {

    private String destnation;
    private int id;


    public SubscribeFrame(int senderId, HashMap headerMap){
        super(senderId);
        this.destnation = headerMap.get("destination").toString();
        this.id = Integer.parseInt(headerMap.get("id").toString());
    }

    @Override
    public String processFrame() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }
}
