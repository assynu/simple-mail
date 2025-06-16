package com.bartczakdawid.features.email.components;

import com.bartczakdawid.core.controls.Clock;
import com.bartczakdawid.core.enums.ViewType;
import com.bartczakdawid.core.managers.ViewManager;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    ViewManager viewManager;

    public MenuBar() {
        this.setLayout(new BorderLayout());

        JButton newEmailButton = new JButton("New Email");
        newEmailButton.addActionListener(_ -> showNewEmail());

        JButton newContactButton = new JButton("Contacts");
        newContactButton.addActionListener(_ -> showContacts());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(newEmailButton);
        leftPanel.add(newContactButton);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        Clock clock = new Clock();
        clock.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(clock, BorderLayout.EAST);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        this.viewManager = ViewManager.getInstance();
    }

    public void showContacts() {
        this.viewManager.toggleView(ViewType.ContactsView, true);
    }

    public void showNewEmail() {
        this.viewManager.toggleView(ViewType.NewEmailView, true);
    }
}
