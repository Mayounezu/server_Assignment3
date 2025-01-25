package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class SendFrame extends Frame {

    String destination;
    String body;

    protected SendFrame(ConcurrentHashMap<String,String> headers, int connectionId, Connections<String> connections, String body) {
        super(headers, connectionId, connections);
        this.commandName = "SEND";
        this.destination = headers.get("destination");
        this.body = body;
        this.recipt = headers.get("receipt");
    }

    @Override
    public void processFrame() {
        boolean canSend = true;
        try {
            checkDest();
            connections.send(destination, this.toString());

        }catch (IOException e){
            ConcurrentHashMap<String, String> errorHeaders = new ConcurrentHashMap<>();
            canSend = false;

            errorHeaders.put("message", e.getMessage());
            errorHeaders.put("Frame", "SEND");

            if(recipt != null)
                errorHeaders.put("receipt-id", recipt);

            FrameHelper.sendError(connectionId, connections, errorHeaders);
        }

        if(canSend){
            sendMessage();

            if(recipt != null)
                FrameHelper.sendReceipt(connectionId, connections, recipt);

        }
    }



    @Override
    public String toString() {
        return "";
    }

    public void checkDest() throws IOException{
        if(destination == null)
            throw new IOException("Destination is missing");
        else if(connections.getClient(connectionId).getChannels().contains(destination))
            throw new IOException("User is not subscribed to this channel");
    }

    public void sendMessage(){
       for(Integer connectionIdIndex : connections.getChannelsToConnectionId().get(destination)){
           if(connectionIdIndex != connectionId){
               MessageFrame messageFrame = new MessageFrame(headers, connectionIdIndex, connections, body);
               connections.send(connectionIdIndex, messageFrame.toString());
           }
       }
    }
}
