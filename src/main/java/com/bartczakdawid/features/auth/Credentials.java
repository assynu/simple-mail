package com.bartczakdawid.features.auth;

public class Credentials {
    private static final String EMAIL_PATTERN = "^.+@.+\\..+$";

    private String email;
    private String password;

    public Credentials(String email, String password) throws IllegalArgumentException {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) throws IllegalArgumentException {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) throws IllegalArgumentException {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        this.password = password;
    }
}
