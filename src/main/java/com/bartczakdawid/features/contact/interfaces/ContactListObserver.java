package com.bartczakdawid.features.contact.interfaces;

import com.bartczakdawid.features.contact.models.Contact;

import java.util.HashSet;

public interface ContactListObserver {
    void onContactListChanged(HashSet<Contact> contacts);
}
