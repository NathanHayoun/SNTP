package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RegisterRestClient(configKey = "resa-api")
public interface ReservationService {

    @Path("reservations/reservation/{id}")
    @GET
    ReservationDTO getReservation(@PathParam("id") Long id);

}
