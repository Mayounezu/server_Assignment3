package bgu.spl.net.impl.stomp;
import java.util.concurrent.ConcurrentHashMap;

public class StompServer {

    private static ConcurrentHashMap<String, String> users;

    public static void main(String[] args) {
        // TODO: implement this
    }


    //if a new user add it to the database, else return wather the password is currect.
    public static boolean isUserExist(String username,String passcode) {
        if(users.get(username) == null){
            users.put(username,passcode);
            return true;
        } else if (users.get(username).equals(passcode))
            return true;

        return false;

    }
}
