import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

public class ThreadGestioneServizioChat implements Runnable {
    private int nMaxConnessioni;
    private List<ThreadChatConnessioni> lista;
    private Thread[] listaThreadConnessioni;
    private Thread me;
    private ServerSocket serverChat;

    public ThreadGestioneServizioChat(int nMaxConnessioni, List<ThreadChatConnessioni> lista) {
        this.nMaxConnessioni = nMaxConnessioni;
        this.lista = lista;
        this.listaThreadConnessioni = new Thread[nMaxConnessioni];
        this.me = new Thread(this);
        this.me.start();
    }

    public void run() {
        boolean continua = true;

        try {
            serverChat = new ServerSocket(9999);
            System.out.println("Server attivo");
        } catch (IOException e) {
            System.out.println("Errore nella creazione del server");
            continua = false;
        }

        if (continua) {
            try {
                for (int i = 0; i < nMaxConnessioni; i++) {
                    Socket tempo = serverChat.accept();
                    ThreadChatConnessioni nuovaConnessione = new ThreadChatConnessioni(this, tempo);
                    lista.add(nuovaConnessione);
                    Thread threadConnessione = new Thread(nuovaConnessione);
                    listaThreadConnessioni[i] = threadConnessione;
                    threadConnessione.start();
                }
                serverChat.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Impossibile creare la connessione");
            }
        }
    }
}
