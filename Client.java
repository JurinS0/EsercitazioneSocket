import java.net.*;
import java.io.*;

public class Client {
    public class static void main(String[] args) throws IoException{
        String hostName = "localhost"; //indirizzo server
        int port = 12345; //porta server

        try (Socket socket = new Socket(hostName, port); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String fromServer;
            while ((fromServer = in.readLine()) != null){
                System.out.println("Server:" + fromServer);
                break;
            }
        }
    }
}