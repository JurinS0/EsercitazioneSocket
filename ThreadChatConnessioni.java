import java.io.*;
import java.net.*;

//questa classe rappresenta una singola connessione di un client a un server

class ThreadChatConnessioni extends Thread {
    private ThreadGestioneServizioChat gestoreChat;
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    private Thread me;

    public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat, Socket client) {
        this.gestoreChat = gestoreChat;
        this.client = client;

        try {
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));//stream input per la comunicazione con il client
            this.output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));//stream output per la comunicazione con il client
        } catch (IOException e) {
            System.out.println("Errore nella creazione del server");
        }

        me = new Thread(this);
        me.start();
    }

    public void run() {//gestisce la recezione dei messaggi inviati dal client
        try {
            String messaggio;
          //rimango in attesa del messaggio mandato al client
            while ((messaggio = input.readLine()) != null) {
                gestoreChat.spedisciMessaggio(messaggio);//ripeto a tutti il messaggio
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura dal client: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura della connessione: " + e.getMessage());
            }
        }
    }

    public void spedisciMessaggio(String messaggio) {
        output.println(messaggio);
    }
}
