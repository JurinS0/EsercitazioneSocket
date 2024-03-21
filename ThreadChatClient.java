import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ThreadChatClient implements Runnable {
    private List lista;
    private Thread me;
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;

    public ThreadChatClient(List lista, String ipServer, int porta) {
        this.lista = lista;
        try {
            client = new Socket(ipServer, porta);
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossibile connettersi al server: " + e.getMessage());
        }
        me = new Thread(this);
        me.start();
    }

    public void run() {
        try {
            while (true) {
                String mes = input.readLine();
                if (mes != null) {
                    lista.add(mes);
                    lista.select(lista.getItemCount() - 1);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore durante la lettura dal server: " + e.getMessage());
        }
    }

    public void spedisciMessaggio(String messaggio) {
        output.println(messaggio);
    }
}
