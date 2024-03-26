import java.awt.*;//interfaccia grafica
import java.awt.event.*;//serve per gestire gli eventi di un'interfaccia grafica
import javax.swing.*;

// Interfaccia grafica del server
public class ChatServer extends JFrame {
    public ChatServer() {
        super("Chat Server");
        this.setSize(new Dimension(500, 300)); // Dimensione della finestra
        this.setLocationRelativeTo(null); // Posiziona la finestra al centro dello schermo
        this.setEnabled(true); // Abilita la finestra
        this.setBackground(Color.blue); // Colore di sfondo della finestra

        PannelloChatServer pan = new PannelloChatServer(); // Crea un nuovo pannello per la chat
        this.getContentPane().add(pan);
        this.setVisible(true);
    }
}

public class PannelloChatServer extends JPanel implements ActionListener {
    private List lista;
    private JTextField textNuovo;
    private ThreadGestioneServizioChat gestioneServizio;

    public PannelloChatServer() {
        super();
        this.setBackground(new Color(50, 100, 225)); //colore di sfondo del pannello

        JPanel panLista = new JPanel(new BorderLayout(20, 5)); //nuovo pannello con layout BorderLayout
        panLista.setBackground(new Color(50, 100, 225)); //colore di sfondo del pannello
        lista = new List(); //nuova lista per visualizzare i messaggi
        lista.setBackground(Color.lightGray);
        lista.setSize(100, 50);
        lista.setVisible(true);

        JLabel chat1 = new JLabel("Chat", JLabel.CENTER); //nuova etichetta per la chat
        chat1.setForeground(new Color(200, 100, 100)); //colore del testo dell'etichetta
        JLabel chat2 = new JLabel("Chat", JLabel.CENTER); //
        chat2.setForeground(new Color(200, 100, 100)); 

        panLista.add(chat1, BorderLayout.WEST);
        panLista.add(lista, BorderLayout.CENTER);
        panLista.add(chat2, BorderLayout.EAST);

        JPanel nuovoMES = new JPanel(new BorderLayout(20, 5));
        nuovoMES.setBackground(new Color(50, 100, 225)); 

        JLabel labNuovo = new JLabel("nuovo messaggio ->", JLabel.CENTER);
        labNuovo.setForeground(new Color(200, 100, 100)); 

        textNuovo = new JTextField(""); //nuovo campo di testo per inserire il nuovo messaggio

        JButton buttonInvia = new JButton("Invia"); 
        buttonInvia.addActionListener(this); 

        nuovoMES.add(labNuovo, BorderLayout.WEST); 
        nuovoMES.add(textNuovo, BorderLayout.CENTER);
        nuovoMES.add(buttonInvia, BorderLayout.EAST); 

        this.setLayout(new BorderLayout(0, 5));
        add(panLista, BorderLayout.CENTER); 
        add(nuovoMES, BorderLayout.SOUTH); 

        connetti(); // Chiama il metodo per connettersi al servizio
    }

    public void connetti() {
        gestioneServizio = new ThreadGestioneServizioChat(10, lista); // Crea un nuovo gestore del servizio di chat
    }

    public void actionPerformed(ActionEvent e) {
        String bottone = e.getActionCommand(); // Ottiene il comando dell'evento
        if (bottone.equals("Invia")) { // Se il comando è "Invia"
            gestioneServizio.spedisciMessaggio(textNuovo.getText()); // Invia il messaggio
            textNuovo.setText(""); // Cancella il testo nel campo di testo
        }
    }
}
