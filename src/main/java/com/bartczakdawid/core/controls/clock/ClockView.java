package com.bartczakdawid.core.controls.clock;

import javax.swing.*;

public class ClockView extends JLabel {
    public ClockView() {
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }
    public void setTimeText(String timeText) {
        SwingUtilities.invokeLater(() -> this.setText(timeText));
    }
}