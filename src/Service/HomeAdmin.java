package Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin extends JFrame {
    private JButton deautentificareButton;
    private JButton vizualizareDatePersonaleButton;
    private JButton cautareUtilizatoriButton;

    public HomeAdmin() {
        setTitle("Home Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        initializeUI();
        setupListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
        deautentificareButton = createStyledButton("Deautentificare");
        vizualizareDatePersonaleButton = createStyledButton("Vizualizare Date");
        cautareUtilizatoriButton = createStyledButton("Cautare Utilizatori");

        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(deautentificareButton)
                        .addComponent(vizualizareDatePersonaleButton)
                        .addComponent(cautareUtilizatoriButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(deautentificareButton)
                .addComponent(vizualizareDatePersonaleButton)
                .addComponent(cautareUtilizatoriButton));
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
            }
        });

        cautareUtilizatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                new HomeAdmin();
            }
        });
    }
}
