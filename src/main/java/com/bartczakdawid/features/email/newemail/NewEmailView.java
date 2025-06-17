package com.bartczakdawid.features.email.newemail;

import com.bartczakdawid.core.controls.dropdown.DropdownView;
import com.bartczakdawid.core.controls.input.InputView;
import com.bartczakdawid.core.navigation.ManageableView;
import com.bartczakdawid.features.contacts.Contact;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewEmailView extends JFrame implements ManageableView {
    private final InputView<JTextField> subjectComponent;
    private final DropdownView<Contact> receiverComponent;
    private final InputView<JTextArea> contentComponent;
    private final JButton sendButton;

    public NewEmailView() {
        this.setTitle("New Email");
        this.setLayout(new BorderLayout());

        JPanel emailInfo = new JPanel();
        emailInfo.setLayout(new BoxLayout(emailInfo, BoxLayout.Y_AXIS));
        emailInfo.setBackground(Color.WHITE);

        this.receiverComponent = new DropdownView<>("Receiver", new Contact[0]);
        this.subjectComponent = new InputView<>("Subject", new JTextField());
        this.contentComponent = new InputView<>("Content", new JTextArea());

        emailInfo.add(receiverComponent);
        emailInfo.add(subjectComponent);

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        content.setBackground(Color.WHITE);
        content.add(emailInfo, BorderLayout.NORTH);
        content.add(contentComponent, BorderLayout.CENTER);

        this.sendButton = new JButton("Send");

        JPanel actionsPanel = new JPanel(new BorderLayout());
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.add(sendButton, BorderLayout.EAST);

        this.add(content, BorderLayout.CENTER);
        this.add(actionsPanel, BorderLayout.SOUTH);
        this.setMinimumSize(new Dimension(200, 300));
        this.setPreferredSize(new Dimension(400, 500));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public Contact getReceiver() {
        return receiverComponent.getValue();
    }

    public String getSubject() {
        return subjectComponent.getText();
    }

    public String getContent() {
        return contentComponent.getText();
    }

    public void setReceiverOptions(List<Contact> contacts) {
        this.receiverComponent.setOptions(contacts.toArray(new Contact[0]));
    }

    public void clearForm() {
        this.subjectComponent.setText("");
        this.contentComponent.setText("");
    }

    @Override
    public void showView() {
        this.clearForm();
        this.setVisible(true);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }
}