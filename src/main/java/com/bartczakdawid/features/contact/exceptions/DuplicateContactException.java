package com.bartczakdawid.features.contact.exceptions;

public class DuplicateContactException extends RuntimeException {
    public DuplicateContactException(String message) {
        super(message);
    }
}
