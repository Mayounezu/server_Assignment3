package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SubscribeFrame extends Frame {

    private String destnation;
    private int id;


    public SubscribeFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections connection){
        super(headers, connectionId, connection);
        this.destnation = headers.get("destination").toString();
        this.id = Integer.parseInt(headers.get("id").toString());
    }

    @Override
    public void processFrame() {
        boolean canSubscribe = true;
        try{
            checkDest();
            checkId();
        }catch (IOException e){
            canSubscribe = false;
            e.printStackTrace();
        }

        if(canSubscribe){
            connections.getClient(connectionId).getChannels().put(id, destnation);
            connections.subscribe(connectionId, id, destnation);
            if(headers.containsKey("receipt-id"))
                FrameHelper.sendReceipt(connectionId, connections, recipt);
        }
    }

    private void checkId() throws IOException {
        if(headers.contains("id")){
            throw new IOException("The frame does not contain id header");
        }else if(connections.getClient(connectionId).getChannels().contains(id)){
            throw new IOException("The client is already subscribed to this channel");
        }
    }

    @Override
    public String toString() {
        return "SUBSCIRBE\n" +
                "destination:" + destnation + "\n" +
                "id:" + id + "\n" +
                "\n" + '\u0000';
    }

    public void checkDest() throws IOException {


    }
}
