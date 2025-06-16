package com.bartczakdawid.features.contacts;

import com.bartczakdawid.core.navigation.ManageableView;

import javax.swing.*;
import java.awt.*;

public class ContactsView extends JFrame implements ManageableView {
    private final JButton newContactButton;
    private final ContactList contactList;

    public ContactsView() {
        this.setTitle("Contacts");
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Contacts");
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        this.newContactButton = new JButton("New contact");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(newContactButton, BorderLayout.EAST);
        topPanel.add(new JSeparator(), BorderLayout.SOUTH);

        this.contactList = new ContactList();
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(300, 500));
        this.setMinimumSize(new Dimension(300, 150));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public JButton getNewContactButton() {
        return newContactButton;
    }

    public ContactList getContactList() {
        return contactList;
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