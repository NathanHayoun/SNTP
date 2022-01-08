package fr.miage.m1.sntp;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/mail")
public class MailResource {

    @Inject Mailer mailer;
    @Inject ReactiveMailer reactiveMailer;


    @GET
    public void sendEmail() {
        mailer.send(
                Mail.withText("etan.guir@gmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application."
                )
        );
    }

//    @GET
//    @Path("/reactive")
//    public Uni<Void> sendEmailUsingReactiveMailer() {
//        return reactiveMailer.send(
//                Mail.withText("etan.guir@gmail.com",
//                        "Ahoy from Quarkus",
//                        "A simple email sent from a Quarkus application using the reactive API."
//                )
//        );
//    }

}