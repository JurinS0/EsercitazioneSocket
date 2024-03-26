import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChatServer server = new ChatServer();
                server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiudi l'applicazione quando la finestra viene chiusa
            }
        });
    }
}

