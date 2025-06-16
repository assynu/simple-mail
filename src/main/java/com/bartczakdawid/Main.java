package com.bartczakdawid;

import com.bartczakdawid.core.navigation.ViewType;
import com.bartczakdawid.features.auth.LoginView;
import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.contacts.ContactsView;
import com.bartczakdawid.features.email.EmailView;
import com.bartczakdawid.features.auth.AccountManager;
import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.features.email.newemail.NewEmailView;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ViewManager viewManager = ViewManager.getInstance();
        AccountManager accountManager;

        try {
            accountManager = AccountManager.getInstance();
        } catch (IOException e) {
            AlertView.showFatalError(e.getMessage());
            return;
        }

        SwingUtilities.invokeLater(() -> {
            viewManager.addView(ViewType.EmailView, new EmailView());
            viewManager.addView(ViewType.ContactsView, new ContactsView());
            viewManager.addView(ViewType.LoginView, new LoginView());
            viewManager.addView(ViewType.NewEmailView, new NewEmailView());

            if (accountManager.isLoggedIn()) {
                viewManager.toggleView(ViewType.EmailView, true);
            } else {
                viewManager.toggleView(ViewType.LoginView, true);
            }
        });
    }
}