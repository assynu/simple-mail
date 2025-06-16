package com.bartczakdawid;

import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.core.navigation.ViewType;
import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.auth.AccountManager;
import com.bartczakdawid.features.auth.LoginController;
import com.bartczakdawid.features.contacts.ContactsController;
import com.bartczakdawid.features.email.EmailController;
import com.bartczakdawid.features.email.newemail.NewEmailController;

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
            ContactsController contactsController = new ContactsController();
            EmailController emailController = new EmailController();
            NewEmailController newEmailController = new NewEmailController();
            LoginController loginController;

            try {
                loginController = new LoginController();
            } catch (IOException e) {
                AlertView.showFatalError(e.getMessage());
                return;
            }

            viewManager.addView(ViewType.EmailView, emailController.getView());
            viewManager.addView(ViewType.ContactsView, contactsController.getView());
            viewManager.addView(ViewType.LoginView, loginController.getView());
            viewManager.addView(ViewType.NewEmailView, newEmailController.getView());

            if (accountManager.isLoggedIn()) {
                viewManager.toggleView(ViewType.EmailView, true);
            } else {
                viewManager.toggleView(ViewType.LoginView, true);
            }
        });
    }
}