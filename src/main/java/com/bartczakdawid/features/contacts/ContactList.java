package com.bartczakdawid.features.contacts;

import com.bartczakdawid.features.alert.AlertView;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

public class ContactList extends JList<Contact> implements ContactListObserver {
    public ContactList() {
        this.setBorder(BorderFactory.createEmptyBorder(4, 8, 8, 8));

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
