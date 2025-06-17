package com.bartczakdawid.core.controls.clock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ClockController {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final ClockView view;

    public ClockController() {
        this.view = new ClockView();
        startClock();
    }

    private void startClock() {
        Thread thread = new Thread(() -> {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    String currentTimeFormatted = LocalTime.now().format(TIME_FORMATTER);
                    view.setTimeText(currentTimeFormatted);
                }
            }, 0, 1000);
        });

        thread.setDaemon(true);
        thread.start();
    }

    public ClockView getView() {
        return view;
    }
}