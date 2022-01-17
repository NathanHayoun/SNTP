package fr.miage.m1.sntp;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;


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