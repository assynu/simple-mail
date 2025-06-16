package com.bartczakdawid.features.email.managers;

import com.bartczakdawid.core.managers.FileManager;
import com.bartczakdawid.features.account.managers.AccountManager;
import com.bartczakdawid.features.account.models.Credentials;
import com.bartczakdawid.features.email.interfaces.EmailListObserver;
import com.bartczakdawid.features.email.models.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

public class EmailManager {
    private static EmailManager emailManagerInstance;

    private final HashSet<Email> emails;
    private final FileManager fileManager;
    private final HashSet<EmailListObserver> emailListObservers;

    private EmailManager() throws IOException {
        this.emails = new HashSet<>();
        this.fileManager = new FileManager("./data", "emails.csv");
        this.emailListObservers = new HashSet<>();

        String[] rawEmails = fileManager.readFile();
        for (String line : rawEmails) {
            String[] rawEmail = line.split(";");
            Email email = new Email(rawEmail[0], rawEmail[1], rawEmail[2], rawEmail[3], LocalDateTime.parse(rawEmail[4]));
            emails.add(email);
        }
    }
    public static EmailManager getInstance() throws IOException {
        if (emailManagerInstance == null) {
            emailManagerInstance = new EmailManager();
        }

        return emailManagerInstance;
    }

    public void sendEmail(String receiver, String subject, String content) throws Exception {
        Credentials credentials = AccountManager.getInstance().getCredentials();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, credentials.getEmail(), credentials.getPassword())
                .buildMailer()) {

            mailer.sendMail(EmailBuilder
                .startingBlank()
                .from(credentials.getEmail())
                .to(receiver)
                .withSubject(subject)
                .withPlainText(content)
                .buildEmail()
            );

            Email sentEmail = new Email(credentials.getEmail(),  receiver, subject, content);
            emails.add(sentEmail);
            fileManager.writeLine(sentEmail.getSender() + ";" + sentEmail.getReceiver() + ";" + sentEmail.getSubject() + ";" + sentEmail.getContent() + ";" + sentEmail.getIsoTime(), true);
            notifyObservers();
        }
    }

    public void addObserver(EmailListObserver emailListObserver) {
        this.emailListObservers.add(emailListObserver);
        emailListObserver.onEmailListChanged(this.emails);
    }

    public void notifyObservers() {
        for (EmailListObserver emailListObserver : this.emailListObservers) {
            emailListObserver.onEmailListChanged(this.emails);
        }
    }
}
