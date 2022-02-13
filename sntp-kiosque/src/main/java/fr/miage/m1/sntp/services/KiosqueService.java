package fr.miage.m1.sntp.services;


import fr.miage.m1.sntp.dto.ReservationDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(configKey = "resa-api")
public interface KiosqueService {

    @Path("reservations/createResa/{gareDepart}/{gareArrive}/{time}/{date}/{voyageurEmail}/{idReservation}")
    @GET
    ReservationDTO getVoyages(
            @PathParam("gareDepart") String gareDepart,
            @PathParam("gareArrive") String gareArrive,
            @PathParam("time") String time,
            @PathParam("date") String date,
            @PathParam("voyageurEmail") String voyageurEmail,
            @PathParam("idReservation") Integer idReservation);
}
