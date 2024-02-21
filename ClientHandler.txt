import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable{
private Socket clientSocket;

pubblic ClientHandler(Socket socket){
this.clientSocket = socket;
}

public void run(){
try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
String input line;
while((inputLine = in.readLine())!= null){

if("leggi db".equalsIgnoreCase(inputLine))
Thread.sleep(4000);

System.out.println("Client" + inputLine);
out.println("ECHO" + inputLine);
if("exit".equalsIgnoreCase(inputLine))
break;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
}
}
