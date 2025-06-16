package com.bartczakdawid.features.contact.components;

import com.bartczakdawid.features.alert.views.AlertView;
import com.bartczakdawid.features.contact.models.Contact;
import com.bartczakdawid.features.contact.interfaces.ContactListObserver;
import com.bartczakdawid.features.contact.managers.ContactManager;
import com.bartczakdawid.features.contact.views.ContactDetailsView;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

public class ContactList extends JList<Contact> implements ContactListObserver {
    public ContactList() {
        this.setBorder(BorderFactory.createEmptyBorder(4, 8, 8, 8));
        this.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Contact selectedContact = this.getSelectedValue();
                if (selectedContact != null) {
                    new ContactDetailsView(selectedContact);
                    this.clearSelection();
                }
            }
        });

        try {
            ContactManager.getInstance().addObserver(this);
        } catch (IOException e) {
            AlertView.showError(e.getMessage());
        }
    }

    @Override
    public void onContactListChanged(HashSet<Contact> contacts) {
        this.setListData(contacts.toArray(Contact[]::new));
    }
}
