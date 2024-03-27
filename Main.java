import javax.swing.*;

public class MainServer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatServer server = new ChatServer();
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiudi l'applicazione quando la finestra viene chiusa

                 ChatClient client =new ChatClient();
              client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

