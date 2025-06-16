package com.bartczakdawid.features.contacts;

import java.util.HashSet;

public interface ContactListObserver {
    void onContactListChanged(HashSet<Contact> contacts);
}
