package com.bartczakdawid.features.email.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Email {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String sender;
    private final String receiver;
    private final String subject;
    private final String content;
    private final LocalTime time;

    public Email(String sender, String receiver, String subject, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.time = LocalTime.now();
    }

    public Email(String sender, String receiver, String subject, String content, LocalTime time) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.time = time;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time.format(TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return this.subject;
    }
}
