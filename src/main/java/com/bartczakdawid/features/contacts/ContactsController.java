package com.bartczakdawid.features.contacts;

import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.contacts.contactdetails.ContactDetailsController;

import java.io.IOException;

public class ContactsController {
    private final ContactsView view;

    public ContactsController() {
        this.view = new ContactsView();
        initListeners();
    }

    private void initListeners() {
        view.getNewContactButton().addActionListener(_ -> {
            try {
                new ContactDetailsController(null);
            } catch (IOException e) {
                AlertView.showError("Could not create new contact: " + e.getMessage());
            }
        });

        view.getContactList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Contact selectedContact = view.getContactList().getSelectedValue();
                if (selectedContact != null) {
                    try {
                        new ContactDetailsController(selectedContact);
                    } catch (IOException ex) {
                        AlertView.showError("Could not open contact details: " + ex.getMessage());
                    }
                }
            }
        });
    }

    public ContactsView getView() {
        return view;
    }
}