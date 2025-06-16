package com.bartczakdawid.features.auth;

import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.core.navigation.ViewType;
import com.bartczakdawid.features.alert.AlertView;

import java.io.IOException;

public class LoginController {
    private final LoginView view;
    private final AccountManager accountManager;
    private final ViewManager viewManager;

    public LoginController() throws IOException {
        this.view = new LoginView();
        this.accountManager = AccountManager.getInstance();
        this.viewManager = ViewManager.getInstance();
        initListeners();
    }

    private void initListeners() {
        view.getLoginButton().addActionListener(_ -> performLogin());
    }

    private void performLogin() {
        try {
            String email = view.getEmail();
            String passwordString = view.getPassword();

            Credentials credentials = new Credentials(email, passwordString);
            accountManager.setCredentials(credentials);
            viewManager.toggleView(ViewType.EmailView, true);
            view.closeView();

        } catch (IOException e) {
            AlertView.showError("Could not save credentials: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            AlertView.showError(e.getMessage());
        } catch (Exception e) {
            AlertView.showError("An unexpected error occurred. Please try again.");
        }
    }

    public LoginView getView() {
        return view;
    }
}