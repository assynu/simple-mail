package com.bartczakdawid.features.email.components;

import com.bartczakdawid.core.controls.TextPresenter;
import com.bartczakdawid.features.email.events.SelectedEmailChangedEvent;
import com.bartczakdawid.features.email.interfaces.SelectedEmailChangeListener;
import com.bartczakdawid.features.email.models.Email;

import javax.swing.*;
import java.awt.*;

public class EmailContent extends JPanel implements SelectedEmailChangeListener {
    private final TextPresenter subjectComponent;
    private final TextPresenter senderComponent;
    private final TextPresenter receiverComponent;
    private final JLabel contentComponent;
    private final JLabel timeComponent;

    public EmailContent() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JPanel emailInfo = new JPanel();
        emailInfo.setLayout(new BoxLayout(emailInfo, BoxLayout.Y_AXIS));
        emailInfo.setBackground(Color.WHITE);

        this.subjectComponent = new TextPresenter("Subject", "Empty");
        this.senderComponent = new TextPresenter("Sender", "Empty");
        this.receiverComponent = new TextPresenter("Receiver", "Empty");
        this.contentComponent = new JLabel("Some email content");
        this.timeComponent = new JLabel("03/06/2025 17:57");
        timeComponent.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        timeComponent.setFont(timeComponent.getFont().deriveFont(12f));

        emailInfo.add(subjectComponent);
        emailInfo.add(new JSeparator());
        emailInfo.add(senderComponent);
        emailInfo.add(receiverComponent);
        emailInfo.add(new JSeparator());

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        content.setBackground(Color.WHITE);
        content.add(emailInfo, BorderLayout.NORTH);
        content.add(contentComponent, BorderLayout.CENTER);
        content.add(timeComponent, BorderLayout.SOUTH);

        this.add(content, BorderLayout.NORTH);
        this.setVisible(false);
    }

    public void setEmail(Email email) {
        subjectComponent.setText(email.getSubject());
        senderComponent.setText(email.getSender());
        receiverComponent.setText(email.getReceiver());
        contentComponent.setText(email.getContent());
        timeComponent.setText(email.getTime());
    }

    @Override
    public void selectedEmailChanged(SelectedEmailChangedEvent emailChangedEvent) {
        Email email = emailChangedEvent.getEmail();

        if (email != null) {
            setEmail(email);
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }
}
