import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 12345; // Porta sulla quale il server ascolta
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server in ascolto sulla porta " + port);

        try {
            while (true) {
                // Stoppa l'esecuzione finché un client non si connette
                Socket clientSocket = serverSocket.accept();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Ciao client!");
                clientSocket.close();
            }
        } finally {
            serverSocket.close();
        }
    }
}
