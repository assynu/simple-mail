package com.bartczakdawid.features.email;

import com.bartczakdawid.core.controls.clock.ClockController;
import com.bartczakdawid.core.controls.clock.ClockView;
import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.core.navigation.ViewType;

import javax.swing.*;
import java.awt.*;

public class EmailActions extends JPanel {
    ViewManager viewManager;

    public EmailActions() {
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

        ClockController clockController = new ClockController();

        this.add(leftPanel, BorderLayout.CENTER);
        this.add(clockController.getView(), BorderLayout.EAST);
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
