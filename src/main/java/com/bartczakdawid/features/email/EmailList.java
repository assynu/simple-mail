package com.bartczakdawid.features.email;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmailList extends JList<Email> {

    private final DefaultListModel<Email> listModel;

    public EmailList() {
        this.listModel = new DefaultListModel<>();
        this.setModel(listModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void setEmailListData(List<Email> emails) {
        listModel.clear();
        for (Email email : emails) {
            listModel.addElement(email);
        }
    }
}