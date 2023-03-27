import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {
    
    // Les éléments de l'interface graphique
    private JTextField messageField;
    private JTextArea chatArea;
    private JButton sendButton;
    
    public App() {
        // Initialisation de la fenêtre principale
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        
        // Création des éléments de l'interface graphique
        messageField = new JTextField();
        chatArea = new JTextArea();
        chatArea.setEditable(false); // Empêche l'utilisateur d'éditer l'historique de la conversation
        sendButton = new JButton("Envoyer");
        
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
            chatArea.append("Moi : " + message + "\n"); // Ajoute le message de l'utilisateur à l'historique de la conversation
            messageField.setText(""); // Efface le champ de texte pour le prochain message
        }
    }
    
    public static void main(String[] args) {
        App app = new App() {
            
        };
    }
}
