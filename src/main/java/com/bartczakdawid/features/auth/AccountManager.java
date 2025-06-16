package com.bartczakdawid.features.auth;

import com.bartczakdawid.core.data.FileManager;

import java.io.IOException;

public class AccountManager {
    private static AccountManager accountManagerInstance;

    private Credentials credentials;
    private final FileManager fileManager;

    private AccountManager() throws IOException {
        this.fileManager = new FileManager("./data", "credentials.txt");
        String[] lines = this.fileManager.readFile();

        if (lines.length == 2) {
            this.credentials = new Credentials(lines[0], lines[1]);
        }
    }

    public static AccountManager getInstance() throws IOException {
        if (accountManagerInstance == null) {
            accountManagerInstance = new AccountManager();
        }

        return accountManagerInstance;
    }

    public boolean isLoggedIn() {
        return this.credentials != null;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) throws IOException {
        this.credentials = credentials;
        this.fileManager.writeLines(new String[] {credentials.getEmail(), credentials.getPassword()}, false);
    }

    public void clearCredentials() throws IOException {
        this.fileManager.writeLine("", false);
    }
}
