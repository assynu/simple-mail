package com.bartczakdawid.core.controls.input;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class Inputview<T extends JTextComponent> extends JPanel {
    private final T inputText;

    public Inputview(String label, T textComponent) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JLabel subjectTitle = new JLabel(label);

        this.inputText = textComponent;
        this.inputText.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));

        this.add(subjectTitle, BorderLayout.NORTH);
        this.add(this.inputText, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 4));
    }

    public String getText() {
        return inputText.getText();
    }

    public void setText(String text) {
        inputText.setText(text);
    }

    public void setEditable(boolean state) {
        this.inputText.setEditable(state);
    }
}
