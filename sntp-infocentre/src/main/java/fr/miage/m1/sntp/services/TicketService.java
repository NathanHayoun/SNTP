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
    String PATH_TICKET_WITH_CORRESPONDANCE = "tickets/ticket/train/now/has/correspondance/{numeroDeTrain}";
    String NB_PASSAGER_BY_TRAIN = "tickets/ticket/train/now/{numeroDeTrain}";
    String PATH_GET_EMAIL_IN_TRAIN = "tickets/ticket/train/email/{numeroDeTrain}";

    @Path(PATH_TICKET_WITH_CORRESPONDANCE)
    @GET
    Integer getNbPassagerByTrainAndHasCorrespondance(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);

    @Path(NB_PASSAGER_BY_TRAIN)
    @GET
    Integer getNbPassagerByTrain(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);

    @Path(PATH_GET_EMAIL_IN_TRAIN)
    @GET
    List<VoyageurDTO> getEmailsByTrainAndNow(@PathParam(NUMERO_DE_TRAIN) int numeroDeTrain);


}
