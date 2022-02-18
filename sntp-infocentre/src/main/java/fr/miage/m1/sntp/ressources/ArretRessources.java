package fr.miage.m1.sntp.ressources;

import fr.miage.m1.sntp.dao.ArretDAO;
import fr.miage.m1.sntp.exceptions.ArretException;
import fr.miage.m1.sntp.models.Arret;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/arrets")
public class ArretRessources {
    public static final String ARRET_ID = "/arret/{id}";
    public static final String ARRET_BY_TRAIN_NUMERO = "/arret/train/{numero}";
    public static final String ID = "id";
    public static final String NUMERO = "numero";
    public static final String ID_GARE = "idGare";
    public static final String ARRET_DEPART_BY_ID_GARE = "/arret/depart/{idGare}";
    public static final String ARRET_ARRIVEE_BY_ID_GARE = "/arret/arrivee/{idGare}";
    @Inject
    ArretDAO arretDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Arret> getArrets() {
        return arretDAO.getAllArret();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ARRET_ID)
    public Arret getArret(@PathParam(ID) long id) {
        try {
            return arretDAO.findArret(id);
        } catch (ArretException arretException) {
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ARRET_BY_TRAIN_NUMERO)
    public List<Arret> getArretByNumTrain(@PathParam(NUMERO) Integer numeroDeTrain) {
        try {
            return arretDAO.getAllArretByNumeroDeTrain(numeroDeTrain);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ARRET_DEPART_BY_ID_GARE)
    public List<Arret> getArretsDepartByGare(@PathParam(ID_GARE) int idGare) {
        try {
            return arretDAO.getArretsDepartByGare(idGare);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ARRET_ARRIVEE_BY_ID_GARE)
    public List<Arret> getArretsArriveeByGare(@PathParam(ID_GARE) int idGare) {
        try {
            return arretDAO.getArretsArriveeByGare(idGare);
        } catch (ArretException arretException) {
            return Collections.emptyList();
        }
    }
}
