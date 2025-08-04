import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChatBot extends JFrame implements ActionListener {
    private JTextArea chatArea;
    private JTextField userInput;
    private JButton sendButton;
    private HashMap<String, String> responses;

    public ChatBot() {
        responses = new HashMap<>();
        responses.put("hello", "Hello! How can I help you?");
        responses.put("what is java", "Java is a high-level, object-oriented programming language.");
        responses.put("what is python", "\"Python is a dynamically typed, interpreted programming language.");
        responses.put("what is artificial intelligence", "Artificial Intelligence is the simulation of human intelligence in machines.");
        responses.put("bye", "Goodbye! Have a nice day!");

        setTitle("Java AI ChatBot");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        userInput = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        userInput.addActionListener(this);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(userInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Basic NLP: lowercasing and removing punctuation
    private String cleanInput(String input) {
        return input.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").trim();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = userInput.getText().trim();
        if (userText.isEmpty()) return;

        chatArea.append("You: " + userText + "\n");
        userInput.setText("");

        String cleaned = cleanInput(userText);
        String botReply = "I'm sorry, I didn't understand that.";

        for (String question : responses.keySet()) {
            if (cleaned.contains(question)) {
                botReply = responses.get(question);
                break;
            }
        }

        chatArea.append("Bot: " + botReply + "\n");
    }

    // THE MAIN METHOD AND "MAIN CLASS" ENTRY POINT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatBot());
    }
}
