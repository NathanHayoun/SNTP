package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.VoyageurDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RegisterRestClient(configKey = "resa-api")
public interface TicketService {

    String NUMERO_DE_TRAIN = "numeroDeTrain";

    @Path("tickets/ticket/train/now/has/correspondance/{numeroDeTrain}")
    @GET
    Integer getNbPassagerByTrainAndHasCorrespondance(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);

    @Path("tickets/ticket/train/now/{numeroDeTrain}")
    @GET
    Integer getNbPassagerByTrain(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);

    @Path("tickets/ticket/train/email/{numeroDeTrain}")
    @GET
    List<VoyageurDTO> getEmailsByTrainAndNow(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);


}
