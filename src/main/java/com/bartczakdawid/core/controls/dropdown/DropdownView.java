package com.bartczakdawid.core.controls.dropdown;

import javax.swing.*;
import java.awt.*;

public class DropdownView<T> extends JPanel {
    private final JComboBox<T> comboBox;

    public DropdownView(String label, T[] options) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JLabel subjectTitle = new JLabel(label);

        this.comboBox = new JComboBox<>(options);
        this.comboBox.setOpaque(false);

        this.add(subjectTitle, BorderLayout.NORTH);
        this.add(this.comboBox, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 4));
    }

    public T getValue() {
        return (T) this.comboBox.getSelectedItem();
    }

    public void setOptions(T[] options) {
        this.comboBox.removeAllItems();
        for (T option : options) {
            this.comboBox.addItem(option);
        }
    }
}
