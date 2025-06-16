package com.bartczakdawid.features.email;

import java.util.EventObject;

public class SelectedEmailChangedEvent extends EventObject {
    private final Email email;

    public SelectedEmailChangedEvent(Object source, Email email) {
        super(source);
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
