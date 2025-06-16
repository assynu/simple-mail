package com.bartczakdawid.features.email.interfaces;

import com.bartczakdawid.features.email.models.Email;

import java.util.HashSet;

public interface EmailListObserver {
    void onEmailListChanged(HashSet<Email> emails);
}
