package com.bartczakdawid.features.email;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class EmailList extends JList<Email> implements EmailListObserver {
    private final HashSet<SelectedEmailChangeListener> listeners;

    public EmailList() {
        this.listeners = new HashSet<>();

        this.addListSelectionListener(_ -> sendSelectedEmailChanged(this.getSelectedValue()));

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void addSelectedEmailChangeListener(SelectedEmailChangeListener selectedEmailChangeListener) {
        this.listeners.add(selectedEmailChangeListener);
    }

    public void sendSelectedEmailChanged(Email email) {
        for (SelectedEmailChangeListener listener : this.listeners) {
            listener.selectedEmailChanged(
                    new SelectedEmailChangedEvent(this, email)
            );
        }
    }

    @Override
    public void onEmailListChanged(HashSet<Email> emails) {
        this.setListData(emails.toArray(Email[]::new));
    }
}
