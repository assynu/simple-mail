package com.bartczakdawid.features.email.interfaces;

import com.bartczakdawid.features.email.events.SelectedEmailChangedEvent;

public interface SelectedEmailChangeListener {
    void selectedEmailChanged(SelectedEmailChangedEvent emailChangedEvent);
}
