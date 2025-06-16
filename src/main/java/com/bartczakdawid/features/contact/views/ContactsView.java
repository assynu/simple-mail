package com.bartczakdawid.features.contact.views;

import com.bartczakdawid.core.interfaces.ManageableView;
import com.bartczakdawid.features.contact.components.ContactList;

import javax.swing.*;
import java.awt.*;

public class ContactsView extends JFrame implements ManageableView {
    public ContactsView() {
        this.setTitle("Contacts");
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Contacts");
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        JButton newContactButton = new JButton("New contact");
        newContactButton.addActionListener(_ -> new ContactDetailsView(null));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(newContactButton, BorderLayout.EAST);
        topPanel.add(new JSeparator(), BorderLayout.SOUTH);

        ContactList contactList = new ContactList();
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(300, 500));
        this.setMinimumSize(new Dimension(300, 150));
        this.pack();
        this.setLocationRelativeTo(null);
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
