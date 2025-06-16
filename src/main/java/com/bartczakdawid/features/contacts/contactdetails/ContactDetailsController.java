package com.bartczakdawid.features.contacts.contactdetails;

import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.contacts.Contact;
import com.bartczakdawid.features.contacts.ContactManager;

import javax.swing.*;
import java.io.IOException;

public class ContactDetailsController {
    private final ContactDetailsView view;
    private final Contact model;
    private final ContactManager contactManager;

    public ContactDetailsController(Contact model) throws IOException {
        this.model = model;
        this.view = new ContactDetailsView(model);
        this.contactManager = ContactManager.getInstance();
        initListeners();
        this.view.setVisible(true);
    }

    private void initListeners() {
        view.getCancelButton().addActionListener(_ -> view.closeView());

        if (model == null) {
            view.getCreateButton().addActionListener(_ -> createContact());
        } else {
            view.getSaveButton().addActionListener(_ -> saveContact());
            view.getDeleteButton().addActionListener(_ -> deleteContact());
        }
    }

    private void createContact() {
        try {
            String firstname = view.getFirstName();
            String lastname = view.getLastName();
            String email = view.getEmail();

            Contact newContact = new Contact(firstname, lastname, email);
            contactManager.createContact(newContact);

            AlertView.showAlert("Success", "New contact has been created", UIManager.getIcon("OptionPane.informationIcon"));
            view.closeView();
        } catch (Exception e) {
            AlertView.showError(e.getMessage());
        }
    }

    private void saveContact() {
        try {
            String firstname = view.getFirstName();
            String lastname = view.getLastName();
            String email = view.getEmail();

            contactManager.updateContact(email, firstname, lastname);

            AlertView.showAlert("Success", "Contact has been updated", UIManager.getIcon("OptionPane.informationIcon"));
            view.closeView();
        } catch (Exception e) {
            AlertView.showError(e.getMessage());
        }
    }

    private void deleteContact() {
        try {
            contactManager.deleteContact(model);

            AlertView.showAlert("Success", "Contact has been deleted", UIManager.getIcon("OptionPane.informationIcon"));
            view.closeView();
        } catch (Exception e) {
            AlertView.showError(e.getMessage());
        }
    }
}