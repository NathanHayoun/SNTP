package fr.miage.m1.sntp.dto;

import java.util.Collection;

public class VoyageDTO {
    private VoyageurDTO voyageur;
    private Collection<TicketDTO> tickets;
    private int nbMaxTickets;

    public VoyageDTO() {
    }

    public VoyageDTO(VoyageurDTO voyageur, Collection<TicketDTO> tickets, int nbMaxTickets) {
        this.voyageur = voyageur;
        this.tickets = tickets;
        this.nbMaxTickets = nbMaxTickets;
    }

    public VoyageurDTO getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(VoyageurDTO voyageur) {
        this.voyageur = voyageur;
    }

    public Collection<TicketDTO> getTickets() {
        return this.tickets;
    }

    public void setTickets(Collection<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public int getNbMaxTickets() {
        return nbMaxTickets;
    }

    public void setNbMaxTickets(int nbMaxTickets) {
        this.nbMaxTickets = nbMaxTickets;
    }
}
