package fr.miage.m1.sntp.dto;

public class TicketDTO {

    private boolean isReservable;
    private Long id_ticket;
    private String villeDepart;
    private String villeArrivee;
    private String typeTrain;

    public TicketDTO() {
    }

    public TicketDTO(boolean is_reservable, Long id_ticket, String villeDepart, String villeArrivee, String typeTrain) {
        this.isReservable = is_reservable;
        this.id_ticket = id_ticket;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.typeTrain = typeTrain;
    }

    public boolean isReservable() {
        return isReservable;
    }

    public void setReservable(boolean is_reservable) {
        this.isReservable = is_reservable;
    }

    public Long getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(Long id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getVilleDepart() {
        return this.villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }
    public String getTypeTrain() {
        return typeTrain;
    }

    public void setTypeTrain(String typeTrain) {
        this.typeTrain = typeTrain;
    }


}
