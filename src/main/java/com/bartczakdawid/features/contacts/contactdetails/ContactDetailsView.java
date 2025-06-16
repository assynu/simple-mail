package com.bartczakdawid.features.contacts.contactdetails;

import com.bartczakdawid.core.controls.input.Inputview;
import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.contacts.ContactManager;
import com.bartczakdawid.features.contacts.Contact;

import javax.swing.*;
import java.awt.*;

public class ContactDetailsView extends JFrame {
    private final Inputview<JTextField> firstNameInput;
    private final Inputview<JTextField> lastNameInput;
    private final Inputview<JTextField> emailInput;

    public ContactDetailsView(Contact contact) {
        JLabel title = new JLabel("Contact details");
        title.setFont(title.getFont().deriveFont(Font.BOLD));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(title, BorderLayout.WEST);
        topPanel.add(new JSeparator(), BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        this.firstNameInput = new Inputview<>("First name", new JTextField());
        this.lastNameInput = new Inputview<>("Last name", new JTextField());
        this.emailInput = new Inputview<>("Email", new JTextField());

        contentPanel.add(this.firstNameInput);
        contentPanel.add(this.lastNameInput);
        contentPanel.add(this.emailInput);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        bottomPanel.setBackground(Color.WHITE);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ -> this.dispose());
        bottomPanel.add(cancelButton, BorderLayout.WEST);

        if (contact == null) {
            this.setTitle("New Contact");
            JButton createButton = new JButton("Create");
            createButton.addActionListener(_ -> this.createContact());

            bottomPanel.add(createButton, BorderLayout.EAST);
        } else {
            this.setTitle("Edit contact");

            this.firstNameInput.setText(contact.getFirstname());
            this.lastNameInput.setText(contact.getLastname());
            this.emailInput.setText(contact.getEmail());
            this.emailInput.setEditable(false);

            JPanel bottomLeftPanel = new JPanel(new BorderLayout());
            bottomLeftPanel.setBackground(Color.WHITE);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(_ -> this.saveContact());
            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(_ -> this.deleteContact(contact));

            bottomLeftPanel.add(saveButton, BorderLayout.EAST);
            bottomLeftPanel.add(deleteButton, BorderLayout.WEST);
            bottomPanel.add(bottomLeftPanel, BorderLayout.EAST);
        }

        this.setLayout(new BorderLayout());

        this.add(topPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setMinimumSize(new Dimension(300, 150));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void createContact() {
        try {
            String firstname = this.firstNameInput.getText();
            String lastname = this.lastNameInput.getText();
            String email = this.emailInput.getText();

            Contact contact = new Contact(firstname, lastname, email);
            ContactManager.getInstance().createContact(contact);

            AlertView.showAlert("Success", "New contact has been created", UIManager.getIcon("OptionPane.informationIcon"));
            this.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertView.showError(e.getMessage());
        }
    }

    public void saveContact() {
        try {
            String firstname = this.firstNameInput.getText();
            String lastname = this.lastNameInput.getText();
            String email = this.emailInput.getText();

            ContactManager.getInstance().updateContact(email, firstname, lastname);

            AlertView.showAlert("Success", "Contact has been updated", UIManager.getIcon("OptionPane.informationIcon"));
            this.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertView.showError(e.getMessage());
        }
    }

    public void deleteContact(Contact contact) {
        try {
            ContactManager.getInstance().deleteContact(contact);

            AlertView.showAlert("Success", "Contact has been deleted", UIManager.getIcon("OptionPane.informationIcon"));
            this.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlertView.showError(e.getMessage());
        }
    }
}
