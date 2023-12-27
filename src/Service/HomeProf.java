package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeProf extends JFrame {
    private JButton deautentificareButton;
    private JButton vizualizareDatePersonaleButton;
    private JButton programareActivitatiButton;
    private JButton catalogButton;
    private JButton vizualizareActivitatiButton;

    public HomeProf() {
        setTitle("Home Profesor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        initializeUI();
        setupListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        deautentificareButton = createStyledButton("Deautentificare");
        vizualizareDatePersonaleButton = createStyledButton("Vizualizare Date");
        programareActivitatiButton = createStyledButton("Programare Activitati");
        catalogButton = createStyledButton("Catalog");
        vizualizareActivitatiButton = createStyledButton("Vizualizare Activitati");

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(deautentificareButton)
                .addComponent(vizualizareDatePersonaleButton)
                .addComponent(programareActivitatiButton)
                .addComponent(catalogButton)
                .addComponent(vizualizareActivitatiButton));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(deautentificareButton)
                .addComponent(vizualizareDatePersonaleButton)
                .addComponent(programareActivitatiButton)
                .addComponent(catalogButton)
                .addComponent(vizualizareActivitatiButton));

        add(panel);
    }

    private void setupListeners() {
        deautentificareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AutentifInterface().setVisible(true);
            }
        });

        vizualizareDatePersonaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adaugă logica pentru butonul "Vizualizare Date Personale"
            }
        });

        programareActivitatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adaugă logica pentru butonul "Programare Activitati"
            }
        });

        catalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adaugă logica pentru butonul "Catalog"
            }
        });

        vizualizareActivitatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adaugă logica pentru butonul "Vizualizare Activitati"
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(67, 134, 204));
        button.setFocusPainted(false);
        return button;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomeProf();
            }
        });
    }
}
