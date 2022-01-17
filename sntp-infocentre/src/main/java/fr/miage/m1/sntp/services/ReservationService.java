package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.NombreDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(configKey = "resa-api")
public interface ReservationService {
    @Path("tickets/ticket/train/now/has/correspondance/{numeroDeTrain}")
    @GET
    NombreDTO getNbPassagerByTrainAndHasCorrespondance(@PathParam("numeroDeTrain") int numeroDeTrain);

    @Path("tickets/ticket/train/now/{numeroDeTrain}")
    @GET
    NombreDTO getNbPassagerByTrain(@PathParam("numeroDeTrain") int numeroDeTrain);


}
