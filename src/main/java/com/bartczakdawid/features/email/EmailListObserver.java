package com.bartczakdawid.features.email;

import java.util.HashSet;

public interface EmailListObserver {
    void onEmailListChanged(HashSet<Email> emails);
}
