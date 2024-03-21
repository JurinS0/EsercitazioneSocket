import java.io.*;
import java.net.*;

public class ThreadChatConnessioni implements Runnable {
private ThreadGestioneServizioChat gestoreChat;
private Socket client = null;
private BufferedReader input = null;
private PrintWriter output = null;
Thread me;

public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat, Socket client){
  this.gestoreChat = gestoreChat;
  this.client = client;

  try{
    this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    this.output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
  }catch(IOException e){
    System.out.println("errore nella creazione del server");
  }

  me = new Thread(this);
  me.start();
}

  public void run(){
    while(true){
      try{
        String mes = null;
        //rimango in attesa del messaggio mandato al client 
        while((mes = input.readLine()) == null){
        System.out.println("messaggio ricevuto");
        }
      }
    }
  }

  public void spedisciMessaggio(String messaggio){
    try{
      output.println(messaggio);
    }catch(Exception e){
      output.println("errore nella spedizione del messaggio");
    }
  }
}
