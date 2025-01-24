package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.impl.stomp.StompServer;

import java.util.HashMap;

public class ConnectFrame extends Frame {

    private final static int argsNum = 4;

    private String acceptVersion;
    private String host;
    private String login;
    private String passcode;


    public ConnectFrame(int connectionId, HashMap headerMap){
        super(connectionId);
        this.acceptVersion = headerMap.get("accept-version").toString();
        this.host = headerMap.get("host").toString();
        this.login = headerMap.get("login").toString();
        this.passcode = headerMap.get("passcode").toString();
    }


    @Override
    public String processFrame() {
        if(StompServer.isUserExist(login, passcode))
            return new ConnectedFrame(connectionId, acceptVersion).toString();
        else
            return new ErrorFrame("User not found").toString(); //should be added.
        return new ConnectedFrame(connectionId, acceptVersion).toString();
    }

    @Override
    public String toString() {
        return "";
    }


    public String getAcceptVersion() {
        return acceptVersion;
    }

    public String getHost() {
        return host;
    }

    public String getLogin() {
        return login;
    }

    public String getPasscode() {
        return passcode;
    }


}
