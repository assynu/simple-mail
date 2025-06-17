package com.bartczakdawid.features.contacts.contactdetails;

import com.bartczakdawid.core.controls.input.InputView;
import com.bartczakdawid.features.contacts.Contact;

import javax.swing.*;
import java.awt.*;

public class ContactDetailsView extends JFrame {
    private final InputView<JTextField> firstNameInput;
    private final InputView<JTextField> lastNameInput;
    private final InputView<JTextField> emailInput;
    private final JButton createButton;
    private final JButton saveButton;
    private final JButton deleteButton;
    private final JButton cancelButton;

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

        this.firstNameInput = new InputView<>("First name", new JTextField());
        this.lastNameInput = new InputView<>("Last name", new JTextField());
        this.emailInput = new InputView<>("Email", new JTextField());

        contentPanel.add(this.firstNameInput);
        contentPanel.add(this.lastNameInput);
        contentPanel.add(this.emailInput);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        bottomPanel.setBackground(Color.WHITE);

        this.cancelButton = new JButton("Cancel");
        bottomPanel.add(this.cancelButton, BorderLayout.WEST);

        if (contact == null) {
            this.setTitle("New Contact");
            this.createButton = new JButton("Create");
            this.saveButton = null;
            this.deleteButton = null;

            bottomPanel.add(this.createButton, BorderLayout.EAST);
        } else {
            this.setTitle("Edit contact");

            this.firstNameInput.setText(contact.getFirstname());
            this.lastNameInput.setText(contact.getLastname());
            this.emailInput.setText(contact.getEmail());
            this.emailInput.setEditable(false);

            this.createButton = null;
            this.saveButton = new JButton("Save");
            this.deleteButton = new JButton("Delete");

            JPanel bottomLeftPanel = new JPanel(new BorderLayout());
            bottomLeftPanel.setBackground(Color.WHITE);
            bottomLeftPanel.add(this.saveButton, BorderLayout.EAST);
            bottomLeftPanel.add(this.deleteButton, BorderLayout.WEST);
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
    }

    public String getFirstName() {
        return firstNameInput.getText();
    }

    public String getLastName() {
        return lastNameInput.getText();
    }

    public String getEmail() {
        return emailInput.getText();
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void closeView() {
        this.dispose();
    }
}