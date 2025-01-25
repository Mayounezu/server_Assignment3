package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class UnsubscribeFrame extends Frame {

    int id;

    protected UnsubscribeFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connections) {
        super(headers, connectionId, connections);
        commandName = "UNSUBSCRIBE";
        id = Integer.parseInt(headers.get("id"));
        recipt = headers.get("receipt");

    }

    @Override
    public void processFrame() {
        boolean canUnsubscribe = true;
        try{
            checkId();
        }catch (IOException e){
            canUnsubscribe = false;

            //send error frame
            ConcurrentHashMap<String, String> errorHeaders = new ConcurrentHashMap<>();

            errorHeaders.put("message", e.getMessage());
            errorHeaders.put("Frame", "UNSUBSCRIBE");

            if(recipt != null)
                errorHeaders.put("receipt-id", recipt);

            FrameHelper.sendError(connectionId, connections, errorHeaders);
        }

        if(canUnsubscribe){
            connections.getClient(connectionId).getChannels().remove(id);
            connections.unsubscribe(connectionId, id, headers.get("destination"));
            if(recipt != null){
                FrameHelper.sendReceipt(connectionId, connections, recipt);
            }
        }
    }

    @Override
    public String toString() {
        return  commandName +"\n" +
                "id:" + id + "\n" +
                "\n" + '\u0000';
    }

    public void checkId() throws IOException{
        if(!headers.contains("id")){
            throw new IOException("id header is missing");
        }else if(!connections.getClient(connectionId).getChannels().containsKey(id)){
            throw new IOException("client isn't subscribed using this subscription id");
        }
    }
}
