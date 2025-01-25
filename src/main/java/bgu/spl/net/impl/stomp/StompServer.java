package bgu.spl.net.impl.stomp;
import bgu.spl.net.api.StompMessagingProtocol;
import bgu.spl.net.srv.Reactor;
import bgu.spl.net.srv.Server;

import java.util.concurrent.ConcurrentHashMap;

public class StompServer {

    private static ConcurrentHashMap<String, String> users;

    public static void main(String[] args){

        if(args.length < 2){
            System.out.println("need 2 args, [port], [ServerType] [numberOfThreads] (optional)");
            System.exit(1); //1 means an error
        }


        int port  = Integer.parseInt(args[0]);
        String serverType = args[1];
        int numberOfThreads = 4;

        if(args.length == 3){
            numberOfThreads = Integer.parseInt(args[2]);
        }

        if(serverType.equals("tpc")){
            Server.threadPerClient(port, ()->new StompProtocolImp(), ()->new FrameEncoderDecoder()).serve();
        } else if (serverType.equals("reactor")){
            Server.reactor(numberOfThreads, port, ()->new StompProtocolImp(), ()->new FrameEncoderDecoder()).serve();
        } else {
            System.out.println("ServerType must be tpc or reactor");
            System.exit(1); //1 means an error
        }
    }



}
