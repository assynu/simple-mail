package com.bartczakdawid.features.email;

import com.bartczakdawid.core.navigation.ManageableView;

import javax.swing.*;
import java.awt.*;

public class EmailView extends JFrame implements ManageableView {
    private final JButton accountButton;
    private final EmailContent emailContent;
    private final EmailList emailList;

    public EmailView() {
        this.setTitle("Simple Mail");
        this.setLayout(new BorderLayout());

        EmailActions emailActions = new EmailActions();
        this.emailContent = new EmailContent();

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(emailActions, BorderLayout.NORTH);
        rightPanel.add(emailContent, BorderLayout.CENTER);

        JLabel label = new JLabel("All Emails");
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setBorder(BorderFactory.createEmptyBorder(16, 8, 16, 0));

        this.accountButton = new JButton("Logout");
        this.accountButton.setFont(this.accountButton.getFont().deriveFont(11f));
        this.accountButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        this.emailList = new EmailList();

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

    public JButton getAccountButton() {
        return accountButton;
    }

    public EmailContent getEmailContent() {
        return emailContent;
    }

    public EmailList getEmailList() {
        return emailList;
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