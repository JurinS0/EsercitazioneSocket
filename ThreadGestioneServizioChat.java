import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

//questa classe serve per gestire i server e le connessioni dei client ad essi

class ThreadGestioneServizioChat implements Runnable {//questo thread si occupa di ricevere le connessioni dei client
    private int nMaxConnessioni;
    private List<String> lista;
    private Thread[] listaThreadConnessioni;
    private Thread me;
    private ServerSocket serverChat;

    public ThreadGestioneServizioChat(int nMaxConnessioni, List<String> lista) {
        this.nMaxConnessioni = nMaxConnessioni;//numero max connessioni
        this.lista = lista;//lista che conterrà le connessioni che in quel momento sono attive
        this.listaThreadConnessioni = new Thread[nMaxConnessioni];
        this.me = new Thread(this);
        this.me.start();
    }

    public void run() {
        boolean continua = true;

        try {
            serverChat = new ServerSocket(9999);//rimane in attesa di connessioni da parte dei client
            System.out.println("Server attivo");
        } catch (IOException e) {
            System.out.println("Errore nella creazione del server");
            continua = false;
        }
//se l'operazione di prima va a buon fine si crea un nuovo thred che conterra ogni connessione
        if (continua) {
            try {
                for (int i = 0; i < nMaxConnessioni; i++) {
                    Socket tempo = serverChat.accept();
                    ThreadChatConnessioni nuovaConnessione = new ThreadChatConnessioni(this, tempo);
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

    public void spedisciMessaggio(String mes) {
    lista.add(mes); //aggiunge il messaggio alla lista dei messaggi visualizzati sul server

    //invio del messaggio a tutti
    for (int i = 0; i < listaThreadConnessioni.length; i++) {
        Thread thread = listaThreadConnessioni[i];
        if (thread != null) {
            ThreadChatConnessioni connessione = (ThreadChatConnessioni) thread;
            connessione.spedisciMessaggio(mes);
        }
    }
}

}
