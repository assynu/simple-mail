package com.bartczakdawid.features.email.views;

import com.bartczakdawid.core.controls.DropdownMenu;
import com.bartczakdawid.core.controls.TextInput;
import com.bartczakdawid.features.alert.views.AlertView;
import com.bartczakdawid.core.interfaces.ManageableView;
import com.bartczakdawid.features.contact.interfaces.ContactListObserver;
import com.bartczakdawid.features.contact.managers.ContactManager;
import com.bartczakdawid.features.contact.models.Contact;
import com.bartczakdawid.features.email.managers.EmailManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class NewEmailView extends JFrame implements ManageableView, ContactListObserver {
    private final TextInput<JTextField> subjectComponent;
    private final DropdownMenu<Contact> receiverComponent;
    private final TextInput<JTextArea> contentComponent;

    public NewEmailView() {
        this.setTitle("New Email");
        this.setLayout(new BorderLayout());

        JPanel emailInfo = new JPanel();
        emailInfo.setLayout(new BoxLayout(emailInfo, BoxLayout.Y_AXIS));
        emailInfo.setBackground(Color.WHITE);

        this.receiverComponent = new DropdownMenu<>("Receiver", new Contact[0]);
        this.subjectComponent = new TextInput<>("Subject", new JTextField());
        this.contentComponent = new TextInput<>("Content", new JTextArea());

        emailInfo.add(receiverComponent);
        emailInfo.add(subjectComponent);

        try {
            ContactManager.getInstance().addObserver(this);
        } catch (IOException e) {
            AlertView.showError(e.getMessage());
        }

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        content.setBackground(Color.WHITE);
        content.add(emailInfo, BorderLayout.NORTH);
        content.add(contentComponent, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(_ -> {
            try {
                sendEmail();
            } catch (Exception e) {
                AlertView.showError("Couldn't send email, " + e.getMessage());
            }
        });

        JPanel actionsPanel = new JPanel(new BorderLayout());
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.add(sendButton, BorderLayout.EAST);

        this.add(content, BorderLayout.CENTER);
        this.add(actionsPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(400, 500));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void sendEmail() throws Exception {
        Contact receiver = this.receiverComponent.getValue();
        String subject = this.subjectComponent.getText();
        String content = this.contentComponent.getText();

        EmailManager.getInstance().sendEmail(receiver.getEmail(), subject, content);
        hideView();
    }

    @Override
    public void showView() {
        this.subjectComponent.setText("");
        this.contentComponent.setText("");

        this.setVisible(true);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }

    @Override
    public void onContactListChanged(HashSet<Contact> contacts) {
        this.receiverComponent.setOptions(contacts.toArray(Contact[]::new));
    }
}
