package com.bartczakdawid.features.auth;

import com.bartczakdawid.core.controls.input.InputView;
import com.bartczakdawid.core.navigation.ManageableView;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame implements ManageableView {
    private final InputView<JTextField> emailInput;
    private final InputView<JPasswordField> passwordInput;
    private final JButton loginButton;

    public LoginView() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        this.emailInput = new InputView<>("Email", new JTextField());
        this.passwordInput = new InputView<>("Password", new JPasswordField());

        contentPanel.add(emailInput);
        contentPanel.add(passwordInput);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        bottomPanel.setBackground(Color.WHITE);

        this.loginButton = new JButton("Login");
        bottomPanel.add(loginButton, BorderLayout.EAST);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setMinimumSize(new Dimension(300, 200));
        this.pack();
    }

    public String getEmail() {
        return emailInput.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordInput.getText());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void closeView() {
        this.dispose();
    }

    @Override
    public void showView() {
        this.setVisible(true);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }
}