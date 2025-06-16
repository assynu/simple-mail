package com.bartczakdawid.features.account.views;

import com.bartczakdawid.core.controls.TextInput;
import com.bartczakdawid.core.enums.ViewType;
import com.bartczakdawid.features.alert.views.AlertView;
import com.bartczakdawid.core.interfaces.ManageableView;
import com.bartczakdawid.features.account.managers.AccountManager;
import com.bartczakdawid.core.managers.ViewManager;
import com.bartczakdawid.features.account.models.Credentials;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JFrame implements ManageableView {
    public LoginView() {
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        TextInput<JTextField> emailInput = new TextInput<>("Email", new JTextField());
        TextInput<JPasswordField> passwordInput = new TextInput<>("Password", new JPasswordField());

        contentPanel.add(emailInput);
        contentPanel.add(passwordInput);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        bottomPanel.setBackground(Color.WHITE);

        JButton cancelButton = new JButton("Login");
        cancelButton.addActionListener(_ -> {
            try {
                Credentials credentials = new Credentials(emailInput.getText(), passwordInput.getText());
                AccountManager.getInstance().setCredentials(credentials);
                ViewManager.getInstance().toggleView(ViewType.EmailView, true);
                this.dispose();
            } catch (IOException e) {
                AlertView.showError("Could not save credentials, " + e.getMessage());
            } catch (IllegalArgumentException e) {
                AlertView.showError(e.getMessage());
            }
        });
        bottomPanel.add(cancelButton, BorderLayout.EAST);

        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setMinimumSize(new Dimension(300, 200));
        this.pack();
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
