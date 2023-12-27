package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Repository.UtilizatorRepository;

public class VizualizareDate extends JFrame {

    public VizualizareDate(String email) {
        setTitle("Vizualizare Date");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);  // Face JTextArea nelaborabilă
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Apelăm metoda din UtilizatorRepository pentru a obține datele
        String rezultat = UtilizatorRepository.vizualizareDatePersonale(email);

        // Afișăm rezultatul în textArea
        textArea.setText(rezultat);

        JButton backButton = createStyledButton("Înapoi");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeStudent(email).setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(67, 134, 204));
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return button;
    }

}
