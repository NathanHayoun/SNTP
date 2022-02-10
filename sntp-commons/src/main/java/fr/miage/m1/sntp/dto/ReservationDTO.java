package fr.miage.m1.sntp.dto;

import java.util.Collection;

public class ReservationDTO {

    private int id;

    int kiosqueId;

    VoyageurDTO acheteur;

    Collection<TicketDTO> tickets;

    int nbVoyageur;

    public ReservationDTO() {
    }

    public ReservationDTO(int kiosqueId, VoyageurDTO acheteur, Collection<TicketDTO> tickets, int nbVoyageur) {
        this.kiosqueId = kiosqueId;
        this.acheteur = acheteur;
        this.tickets = tickets;
        this.nbVoyageur = nbVoyageur;
    }

    public void setId(Long id) {
    }

    public int getId() {
        return id;
    }

    public int getKiosqueId() {
        return kiosqueId;
    }

    public void setKiosqueId(int kiosqueId) {
        this.kiosqueId = kiosqueId;
    }

    public VoyageurDTO getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(VoyageurDTO acheteur) {
        this.acheteur = acheteur;
    }

    public Collection<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public int getNbVoyageur() {
        return nbVoyageur;
    }

    public void setNbVoyageur(int nbVoyageur) {
        this.nbVoyageur = nbVoyageur;
    }
}
