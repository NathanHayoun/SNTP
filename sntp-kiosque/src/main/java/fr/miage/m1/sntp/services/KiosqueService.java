package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Quentin Vaillant
 */
@RegisterRestClient(configKey = KiosqueService.RESA_API_CONFIG_KEY)
public interface KiosqueService {

    String PATH_FOR_CREATE_RESERVATION = "reservations/createResa/{gareDepart}/{gareArrive}/{time}/{date}/{voyageurEmail}/{idReservation}";
    String GARE_DEPART = "gareDepart";
    String GARE_ARRIVE = "gareArrive";
    String TIME = "time";
    String DATE = "date";
    String VOYAGEUR_EMAIL = "voyageurEmail";
    String ID_RESERVATION = "idReservation";
    String RESA_API_CONFIG_KEY = "resa-api";

    @Path(PATH_FOR_CREATE_RESERVATION)
    @GET
    ReservationDTO getVoyages(
            @PathParam(GARE_DEPART) String gareDepart,
            @PathParam(GARE_ARRIVE) String gareArrive,
            @PathParam(TIME) String time,
            @PathParam(DATE) String date,
            @PathParam(VOYAGEUR_EMAIL) String voyageurEmail,
            @PathParam(ID_RESERVATION) Integer idReservation);
}
