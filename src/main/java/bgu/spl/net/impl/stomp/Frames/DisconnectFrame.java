package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class DisconnectFrame extends Frame {



    protected DisconnectFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connections) {
        super(headers, connectionId, connections);
        this.recipt = headers.get("receipt-id");
        this.commandName = "DISCONNECT";
    }

    @Override
    public void processFrame() {

        boolean canDisconnect = true;

        try{
            checkreceipt();
        }catch (IOException e){
            canDisconnect = false;
            ConcurrentHashMap<String, String> errorHeaders = new ConcurrentHashMap<>();

            errorHeaders.put("message", e.getMessage());
            errorHeaders.put("Frame", commandName);

            FrameHelper.sendError(connectionId, connections, errorHeaders);
        }

        if(canDisconnect){
            connections.disconnect(connectionId);
            FrameHelper.sendReceipt(connectionId, connections, recipt);
        }
    }

    private void checkreceipt() throws IOException {
        if(recipt == null)
            throw new IOException("The receipt-id is missing");
    }

    @Override
    public String toString() {
        return "";
    }
}
