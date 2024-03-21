import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//interfaccia grafica del client

public class ChatServer extends JFrame{
  public ChatServer(){
    super("Chat Server");
    this.setSize(new Dimension(500,300));//grandezza della finestra
    this.setLocationRelativeTo(null);//la posiziono al centro dello schermo
    this.setEnabled(true);//proprieta enable
    this.setBackground(Color.green);//colore sfondo
//creazione del pannello per la visualizzazione e l'inserimento dei messaggi
    PannelloChatServer pan = new PannelloChatServer();
    this.getContentPane().add(pan);
    this.SetVisible(true);
  }
}

public class PannelloChatServer extends JPanel {
  private List lista; // Declare lista as a field

  public PannelloChatServer() {
    super();
    this.setBackground(new Color(50, 100, 225));
    //pannello superiore(output)
    JPanel panLista = new Jpanel(new BorderLayout(20, 5));
    panLista.setBackground(new Color(50, 100, 225));
    lista = new List(); // Initialize lista
    lista.setBackground(Color. lightGray);
    lista.setSize(100, 50);
    lista.setVisible(true);
//scritte laterali
    JLabel chat1 = new JLabel("Chat", JLabel.CENTER);
    chat1.setForeground(new Color(200, 100, 100));
    JLabel chat2 = new JLabel("Chat", JLabel.CENTER);
    chat2.setForeground(new Color(200, 100, 100));

    panLista.add(chat1, BorderLayout.WEST);
    panLista.add(lista, BorderLayout.CENTER);
    panLista.add(chat2, BorderLayout.EAST);
//pannello inserimento nuovo messaggio
    JPanel nuovoMES = new JPanel(new BorderLayout(20, 5));
    nuovoMES.setBackground(new Color(50, 100, 225));

    JLabel labNuovo = new JLabel("nuovo messaggio ->", JLabel.CENTER);
    labNuovo.setForeground(new Color(200, 100, 100));
    
    textNuovo = new JTextField("");

    JButton buttonInvia = new JButton("Invia");
    buttonInvia.addActionListener(this);

    nuovoMES.add(labNuovo, BorderLayout.WEST);
    nuovoMES.add(labNuovo, BorderLayout.CENTER);
    nuovoMES.add(labNuovo, BorderLayout.EAST);

    this.setLayout(new BorderLayout(0,5));
    add(panLista, BorderLayout.CENTER);
    add(nuovoMES, BorderLayout.SOUTH);

    connetti();
  }
}

public void connetti(){
  gestioneServizio = new ThreadGestioneServizioChat(10,lista);
}

public void actionPerformed(ActionEvent e){
  String bottone = e.getActionCommand();
if(bottone.equals("invia")){
  gestioneServizio.spedisciMessaggio(textNuovo.getText());
  textNuovo.setText("");
}
}
