package fr.miage.m1.sntp.utils;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import java.util.List;

public class LibMail {

    private LibMail() {

    }

    public static void sendMailWithBcc(Mailer mailer, List<String> mails, String subject, String text) {
        Mail mail = new Mail();
        mail.setBcc(mails).setSubject(subject).setText(text);
        mailer.send(mail);
    }

    public static void sendEmail(Mailer mailer, String receiver, String subject, String text) {
        mailer.send(
                Mail.withText(receiver,
                        subject,
                        text
                )
        );
    }

}