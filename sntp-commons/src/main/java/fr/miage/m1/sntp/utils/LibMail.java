package fr.miage.m1.sntp.utils;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import java.util.List;

public class MailResource {

    @Inject
    Mailer mailer;

    public void sendMailWithBcc(List<String> mails, String subject, String text) {
        Mail mail = new Mail();
        mail.setBcc(mails).setSubject(subject).setText(text);
        mailer.send(mail);
    }

    @GET
    public void sendEmail(String receiver, String subject, String text) {
        mailer.send(
                Mail.withText(receiver,
                        subject,
                        text
                )
        );
    }

}