package com.bartczakdawid.features.alert;

import javax.swing.*;
import java.awt.*;

public class AlertView extends JFrame {
    private static AlertView alert;

    private final JLabel iconLabel;
    private final JLabel descriptionLabel;
    private final JButton button;

    private AlertView() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.iconLabel = new JLabel();
        this.iconLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        this.add(this.iconLabel, BorderLayout.WEST);

        this.descriptionLabel = new JLabel();
        this.descriptionLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        this.add(this.descriptionLabel);

        this.button = new JButton("Ok");
        this.button.addActionListener(_ -> this.setVisible(false));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.button);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(400, 150));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void showAlert(String title, String description, Icon icon) {
        if (alert == null) {
            alert = new AlertView();
        }

        alert.setTitle(title);
        alert.iconLabel.setIcon(icon);
        alert.descriptionLabel.setText(description);

        alert.setVisible(true);
    }

    public static void showError(String error) {
        showAlert("Error", error, UIManager.getIcon("OptionPane.errorIcon"));
    }

    public static void showFatalError(String error) {
        showAlert("Fatal Error", error, UIManager.getIcon("OptionPane.errorIcon"));
        alert.button.setText("Exit");
        alert.button.addActionListener(_ -> alert.dispose());
        alert.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
