package com.bartczakdawid.features.contacts;

import com.bartczakdawid.core.data.FileManager;

import java.io.*;
import java.util.HashSet;

public class ContactManager {
    private static ContactManager contactManagerInstance;

    private final FileManager fileManager;
    private final HashSet<Contact> contacts;
    private final HashSet<ContactListObserver> contactListObservers;

    private ContactManager() throws IOException {
        this.contacts = new HashSet<>();
        this.contactListObservers = new HashSet<>();
        this.fileManager = new FileManager("./data", "contacts.csv");

        String[] rawContacts = fileManager.readFile();
        for (String line : rawContacts) {
            String[] contactParts = line.split(";");
            Contact contact = new Contact(contactParts[0], contactParts[1], contactParts[2]);
            contacts.add(contact);
        }
    }

    public static ContactManager getInstance() throws IOException {
        if (contactManagerInstance == null) {
            contactManagerInstance = new ContactManager();
        }

        return contactManagerInstance;
    }

    public void createContact(Contact contact) throws DuplicateContactException, IOException {
        if (this.contacts.contains(contact)) {
            throw new DuplicateContactException("Contact with this email address already exists");
        }

        this.contacts.add(contact);
        notifyObservers();

        fileManager.writeLine(contact.getFirstname() + ";" + contact.getLastname() + ";" + contact.getEmail(), true);
    }

    public void deleteContact(Contact contact) throws IllegalArgumentException, IOException {
        boolean removed;

        removed = this.contacts.removeIf(thisContact -> thisContact.getEmail().equals(contact.getEmail()));
        if (!removed) {
            throw new IllegalArgumentException("Contact with email " + contact.getEmail() + " not found");
        }

        notifyObservers();

        String[] lines = contacts.stream()
                .map(thisContact -> thisContact.getFirstname() + ";" + thisContact.getLastname() + ";" + thisContact.getEmail())
                .toArray(String[]::new);

        fileManager.writeLines(lines, false);
    }

    public void updateContact(String email, String firstname, String lastname) throws IOException, IllegalArgumentException {
        for (Contact contact : this.contacts) {
            if (contact.getEmail().equals(email)) {
                contact.setFirstname(firstname);
                contact.setLastname(lastname);
            }
        }

        notifyObservers();

        String[] lines = contacts.stream()
                .map(thisContact -> thisContact.getFirstname() + ";" + thisContact.getLastname() + ";" + thisContact.getEmail())
                .toArray(String[]::new);

        fileManager.writeLines(lines, false);
    }

    public HashSet<Contact> getContacts() {
        return this.contacts;
    }

    public void addObserver(ContactListObserver contactListObserver) {
        this.contactListObservers.add(contactListObserver);
        contactListObserver.onContactListChanged(this.contacts);
    }

    public void notifyObservers() {
        for (ContactListObserver contactListObserver : this.contactListObservers) {
            contactListObserver.onContactListChanged(this.contacts);
        }
    }
}
