package com.bartczakdawid.features.email;

import com.bartczakdawid.core.navigation.ViewType;
import com.bartczakdawid.core.navigation.ManageableView;
import com.bartczakdawid.features.auth.AccountManager;
import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.features.auth.Credentials;
import com.bartczakdawid.features.alert.AlertView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmailView extends JFrame implements ManageableView {
    private final JButton accountButton;

    public EmailView() {
        this.setTitle("Simple Mail");
        this.setLayout(new BorderLayout());

        ActionMenu actionMenu = new ActionMenu();
        EmailContent emailContent = new EmailContent();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(actionMenu, BorderLayout.NORTH);
        rightPanel.add(emailContent, BorderLayout.CENTER);

        JLabel label = new JLabel("All Emails");
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setBorder(BorderFactory.createEmptyBorder(16, 8, 16, 0));

        this.accountButton = new JButton();
        this.accountButton.setFont(this.accountButton.getFont().deriveFont(11f));
        this.accountButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        this.accountButton.addActionListener(_ -> {
            try {
                AccountManager.getInstance().clearCredentials();
                ViewManager viewManager = ViewManager.getInstance();
                viewManager.hideAllViews();
                viewManager.toggleView(ViewType.LoginView, true);
            } catch (IOException e) {
                AlertView.showError("Could not logout, " + e.getMessage());
            }
        });

        EmailList emailList = new EmailList();
        emailList.addSelectedEmailChangeListener(emailContent);

        try {
            EmailManager.getInstance().addObserver(emailList);
        } catch (IOException e) {
            AlertView.showError("Could not sync saved emails, " + e.getMessage());
        }

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(200, 0));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(label, BorderLayout.NORTH);
        leftPanel.add(emailList, BorderLayout.CENTER);
        leftPanel.add(accountButton, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(800, 600));
        this.setMinimumSize(new Dimension(500, 300));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void showView() {
        try {
            Credentials credentials = AccountManager.getInstance().getCredentials();
            this.accountButton.setText(credentials.getEmail());
        } catch (IOException e) {
            AlertView.showError("Could not retrieve account email, " + e.getMessage());
        }

        this.setVisible(true);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }
}
