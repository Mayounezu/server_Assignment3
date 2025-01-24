package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;
import bgu.spl.net.impl.stomp.StompServer;
import bgu.spl.net.srv.Connections;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectFrame extends Frame {

    private final static int argsNum = 4;

    private String acceptVersion;
    private String host;
    private String login;
    private String passcode;


    public ConnectFrame(ConcurrentHashMap<String, String> headers, int connectionId, Connections<String> connection){
        super(headers, connectionId, connection);
        this.acceptVersion = this.headers.get("accept-version");
        this.host = this.headers.get("host");
        this.login = this.headers.get("login");
        this.passcode = this.headers.get("passcode");
    }


    @Override
    public void processFrame() {
        boolean canLogin = true;
        try{
            checkAcceptVer();
            checkHost();
            checkLogin();
        } catch (IOException e){
            canLogin = false;
            //create an error frame
        }

        if(canLogin){
            connections.login(connectionId, login, passcode);
            FrameHelper.sendConnected(connectionId, connections, acceptVersion);
            //need to handle recipt
        }
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

    //checkers

    public void checkAcceptVer() throws IOException {
        if(acceptVersion == null)
            throw new IOException("Accept-Version is missing");
        else if (acceptVersion.equals(FrameHelper.StompVersion))
            throw new IOException("Accept-Version is not the same as the server.");{

        }
    }

    public void checkHost() throws IOException {
        if(host == null)
            throw new IOException("Host is missing");
        else if(!host.equals(FrameHelper.Host))
            throw new IOException("Host is not the same as the server.");
    }

    public void checkLogin() throws IOException {
        if(login == null || passcode == null)
            throw new IOException("Login or passcode is missing");
        else if(connections.isUserInfoValid(login, passcode))
            throw new IOException("username does not match password");
        else if (connections.isUserLoggedIn(login))
            throw new IOException("User is already logged in");

    }


}
