package com.bartczakdawid.features.contacts;

import java.util.Objects;

public class Contact {
    private static final String EMAIL_PATTERN = "^.+@.+\\..+$";

    private String firstname;
    private String lastname;
    private String email;

    public Contact(String firstname, String lastname, String email) throws IllegalArgumentException {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) throws IllegalArgumentException {
        if (firstname.isEmpty()) {
            throw new IllegalArgumentException("Contact first name cannot be empty");
        }

        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        if (lastname.isEmpty()) {
            throw new IllegalArgumentException("Contact last name cannot be empty");
        }

        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Contact email cannot be empty");
        }

        if (!email.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.email = email;
    }


    @Override
    public String toString() {
        return this.getFirstname() + " " + this.getLastname() + "\t(" + this.getEmail() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
