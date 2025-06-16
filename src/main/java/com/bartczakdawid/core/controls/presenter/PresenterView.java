package com.bartczakdawid.core.controls.presenter;

import javax.swing.*;
import java.awt.*;

public class PresenterView extends JPanel {
    private final JLabel subjectText;

    public PresenterView(String label, String text) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
        this.setBackground(Color.WHITE);
        JLabel subjectTitle = new JLabel(label + ": ");
        subjectTitle.setFont(subjectTitle.getFont().deriveFont(Font.BOLD));
        this.add(subjectTitle);
        this.subjectText = new JLabel(text);
        this.add(this.subjectText);
    }

    public void setText(String text) {
        subjectText.setText(text);
    }
}
