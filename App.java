import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class App extends JFrame implements ActionListener {
    
    // Les éléments de l'interface graphique
    private JTextField messageField;
    private JTextArea chatArea;
    private JButton sendButton;
    private void sendMessage() {
        String message = messageField.getText();
        if (!message.equals("")) {
            // Obtenir l'heure actuelle
            LocalTime time = LocalTime.now();
            // Ajouter le message et l'heure à l'historique de conversation
            chatArea.append("Moi : " + message + "  " + time.getHour() + ":" + time.getMinute() +  "\n");
            messageField.setText("");
        }
    }
    
    public App() {
        // Initialisation de la fenêtre principale
        setTitle("Discord");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        
        // Création des éléments de l'interface graphique
        messageField = new JTextField();
        messageField.setBackground(new Color(110, 110, 110));
        messageField.setForeground(Color.WHITE);
        chatArea = new JTextArea();
        chatArea.setEditable(false); // Empêche l'utilisateur d'éditer l'historique de la conversation
        chatArea.setBackground(new Color(44, 47, 51));
        chatArea.setForeground(Color.WHITE);
        sendButton = new JButton("Envoyer");
        messageField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        
        // Ajout des éléments à la fenêtre principale
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(chatArea, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        container.add(bottomPanel, BorderLayout.SOUTH);
        
        // Ajout de l'action sur le bouton d'envoi
        sendButton.addActionListener(this);
        
        // Affichage de la fenêtre principale
        setVisible(true);

    }
    
    // Fonction appelée lorsqu'on appuie sur le bouton d'envoi
    public void actionPerformed(ActionEvent e) {
        String message = messageField.getText();
        if (!message.equals("")) {
            LocalTime time = LocalTime.now();
            chatArea.append("Moi : " + message + "  " + time.getHour() + ":" + time.getMinute() +  "\n"); // Ajoute le message de l'utilisateur à l'historique de la conversation
            messageField.setText(""); // Efface le champ de texte pour le prochain message
        }
    }
    
    public static void main(String[] args) {
        App app = new App() {
            
        };
    }
}
