import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//questa classe rappresenta l'implementazione del servizio client della chat
//in questo caso il client ha solo il compito di connetersi con il server e rimanere in attesa 
//dei messaggi inviati dal servere e su richiesta dell'utente deve inviare messaggi verso il server

public class ThreadChatClient implements Runnable {
    private List lista;
    private Thread me;
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;

    public ThreadChatClient(List lista, String ipServer, int porta) {
        this.lista = lista;
        try {
            client = new Socket(ipServer, porta);//inizializzazione di un socket per la connessione al server
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Impossibile connettersi al server: " + e.getMessage());
        }
        me = new Thread(this);
        me.start();
    }

    public void run() { //gestisce la ricezione dei messaggi da parte del server
        try {
            while (true) {
                String mes = input.readLine();
                if (mes != null) {
                    lista.add(mes); //i messaggi vengono inseriti in una lista 
                    lista.select(lista.getItemCount() - 1);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore durante la lettura dal server: " + e.getMessage());
        }
    }

    public void spedisciMessaggio(String messaggio) { //utilizzato per spedire messaggi al server
        output.println(messaggio);
    }
}
