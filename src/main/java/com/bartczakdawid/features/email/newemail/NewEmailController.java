package com.bartczakdawid.features.email.newemail;

import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.contacts.Contact;
import com.bartczakdawid.features.contacts.ContactListObserver;
import com.bartczakdawid.features.contacts.ContactManager;
import com.bartczakdawid.features.email.EmailManager;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

public class NewEmailController implements ContactListObserver {
    private final NewEmailView view;
    private final EmailManager emailManager;
    private final ContactManager contactManager;

    public NewEmailController() {
        this.view = new NewEmailView();
        try {
            this.emailManager = EmailManager.getInstance();
            this.contactManager = ContactManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initListeners();
        contactManager.addObserver(this);
    }

    private void initListeners() {
        view.getSendButton().addActionListener(_ -> sendEmail());
    }

    private void sendEmail() {
        view.getSendButton().setEnabled(false);
        try {
            Contact receiver = view.getReceiver();
            String subject = view.getSubject();
            String content = view.getContent();

            if (receiver == null) {
                AlertView.showError("Please select a receiver.");
                return;
            }

            emailManager.sendEmail(receiver.getEmail(), subject, content);
            AlertView.showAlert("Success", "Email sent!", UIManager.getIcon("OptionPane.informationIcon"));
            view.hideView();
        } catch (Exception e) {
            view.getSendButton().setEnabled(true);
            AlertView.showError("Couldn't send email: " + e.getMessage());
        }
    }

    @Override
    public void onContactListChanged(HashSet<Contact> contacts) {
        view.setReceiverOptions(contacts.stream().toList());
    }

    public NewEmailView getView() {
        return view;
    }
}