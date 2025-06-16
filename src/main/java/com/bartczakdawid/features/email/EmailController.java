package com.bartczakdawid.features.email;

import com.bartczakdawid.core.navigation.ViewManager;
import com.bartczakdawid.core.navigation.ViewType;
import com.bartczakdawid.features.alert.AlertView;
import com.bartczakdawid.features.auth.AccountManager;
import com.bartczakdawid.features.auth.Credentials;

import java.io.IOException;
import java.util.HashSet;

public class EmailController implements EmailListObserver {
    private final EmailView view;
    private final AccountManager accountManager;
    private final ViewManager viewManager;
    private final EmailManager emailManager;

    public EmailController() {
        try {
            this.accountManager = AccountManager.getInstance();
            this.emailManager = EmailManager.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.view = new EmailView();
        this.viewManager = ViewManager.getInstance();

        initListeners();
        setupEmailSync();
    }

    private void initListeners() {
        view.getAccountButton().addActionListener(_ -> performLogout());

        view.getEmailList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Email selectedEmail = view.getEmailList().getSelectedValue();
                view.getEmailContent().selectedEmailChanged(new SelectedEmailChangedEvent(view.getEmailList(), selectedEmail));
            }
        });
    }

    private void performLogout() {
        try {
            accountManager.clearCredentials();
            viewManager.hideAllViews();
            viewManager.toggleView(ViewType.LoginView, true);
        } catch (IOException e) {
            AlertView.showError("Could not logout: " + e.getMessage());
        }
    }

    private void setupEmailSync() {
        emailManager.addObserver(this);
    }

    @Override
    public void onEmailListChanged(HashSet<Email> emails) {
        view.getEmailList().setEmailListData(emails.stream().toList());
    }

    public EmailView getView() {
        return view;
    }
}