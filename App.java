import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class App extends JFrame implements ActionListener {

    // Les éléments de l'interface graphique
    private JTextField messageField;
    private JTextArea chatArea;
    private JButton sendButton;
    private JLabel fontLabel;
    private JTextField fontField;
    private JComboBox<String> fontComboBox;

    public App() {
        // Initialisation de la fenêtre principale
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);

        // Création des éléments de l'interface graphique
        messageField = new JTextField();
        chatArea = new JTextArea();
        chatArea.setLineWrap(true);
        chatArea.setEditable(false); // Empêche l'utilisateur d'éditer l'historique de la conversation
        sendButton = new JButton("Envoyer");
        fontLabel = new JLabel("Taille de police : ");
        fontField = new JTextField("12"); // Valeur par défaut

        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); // Récupère la liste des noms de police disponibles
        for (File file : new File(".").listFiles()) { // Parcourt tous les fichiers du répertoire de l'application
            if (file.isFile() && (file.getName().endsWith(".ttf") || file.getName().endsWith(".otf"))) { // Vérifie si le fichier est une police
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, file); // Crée un objet Font à partir du fichier de police
                    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font); // Enregistre la police dans l'environnement graphique local
                    fontNames = Arrays.copyOf(fontNames, fontNames.length + 1); // Ajoute le nom de la police à la liste des noms de police
                    fontNames[fontNames.length - 1] = font.getName(); // Ajoute le nom de la police à la fin de la liste des noms de police
                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        fontComboBox = new JComboBox<>(fontNames); // Crée un nouveau menu déroulant avec les noms de police

        // Ajout des éléments à la fenêtre principale
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel fontPanel = new JPanel(new FlowLayout()); // Panel pour contenir le label, le champ de texte pour la taille de police et le menu déroulant pour la police
        fontPanel.add(fontLabel);
        fontPanel.add(fontField);
        fontPanel.add(fontComboBox);
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(fontPanel, BorderLayout.NORTH); // Ajoute le panel de la taille de police et du menu déroulant en haut du panel du bas
        container.add(bottomPanel, BorderLayout.SOUTH);

        // Ajout de l'action sur le bouton d'envoi
        sendButton.addActionListener(this);

        // Ajout du KeyListener au champ de texte "messageField"
        messageField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    actionPerformed(new ActionEvent(sendButton, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

        // Affichage de la fenêtre principale
        setVisible(true);
    }

    // Fonction appelée lorsqu'on appuie sur le bouton d'envoi
    public void actionPerformed(ActionEvent e) {
        String message = messageField.getText();
        int size = Integer.parseInt(fontField.getText()); // Récupère la taille de police saisie
        Font font = new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, size); // Crée une nouvelle police avec la taille saisie et la police sélectionnée dans le menu déroulant
        messageField.setFont(font);
        chatArea.setFont(font);
        if (!message.equals("")) {
            chatArea.append("Moi : " + message + "\n"); // Ajoute le message de l'utilisateur à l'historique de la conversation
            messageField.setText(""); // Efface le champ de texte pour le prochain message
        }
    }
    public static void main(String[] args) {
        App app = new App();
    }
}