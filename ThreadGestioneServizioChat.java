import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

//questa classe serve per gestire i server e le connessioni dei client ad essi

public class ThreadGestioneServizioChat implements Runnable { //questo thread si occupa di ricevere le connessioni dei client
    private int nMaxConnessioni;//numero max connessioni 
    private List<ThreadChatConnessioni> lista;//lista che conterrà le connessioni che in quel momento sono attive
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

    public void spedisciMessaggio(String mes){
        //scrittura del messaggio
        lista.add(mes);
        lista.select(lista.getItemCount()-1);
//invio del messaggio a tutti i client
        for(int xx=0; xx<this.nrMaxCknnessioni; xx++){
            if(listaConnessioni[xx] != null){
                listaConnessioni[xx].spedisciMessaggioChat(mes);
       }
   }
    }
