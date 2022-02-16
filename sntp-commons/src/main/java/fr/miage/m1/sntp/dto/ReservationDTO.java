package fr.miage.m1.sntp.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aicha Nur
 */
public class ReservationDTO {
    private VoyageurDTO voyageur;
    private List<TicketDTO> tickets;
    private int id;
    private LocalDate dateDeReservation;
    private double prix;

    public VoyageurDTO getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(VoyageurDTO voyageur) {
        this.voyageur = voyageur;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDeReservation() {
        return dateDeReservation;
    }

    public void setDateDeReservation(LocalDate dateDeReservation) {
        this.dateDeReservation = dateDeReservation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "voyageur=" + voyageur +
                ", tickets=" + tickets +
                ", id=" + id +
                ", dateDeReservation=" + dateDeReservation +
                ", prix=" + prix +
                '}';
    }
}
