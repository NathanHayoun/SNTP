package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Quentin Vaillant
 */
@RegisterRestClient(configKey = ReservationService.RESA_API_CONFIG_KEY)
public interface ReservationService {

    String RESA_API_CONFIG_KEY = "resa-api";
    String PATH_FOR_GET_RESERVATION_FOR_API = "reservations/reservation/{id}";
    String ID = "id";

    @Path(PATH_FOR_GET_RESERVATION_FOR_API)
    @GET
    ReservationDTO getReservation(@PathParam(ID) Long id);

}
