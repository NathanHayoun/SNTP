package fr.miage.m1.sntp.dto;

public class TicketDTO {

    private boolean isReservable;
    private Long id_ticket;
    private String gareDepart;
    private String gareArrivee;
    private String typeTrain;
    private String dateDepart;

    public TicketDTO() {
    }

    public TicketDTO(boolean is_reservable, Long id_ticket, String gareDepart, String gareArrivee, String typeTrain, String dateDepart) {
        this.isReservable = is_reservable;
        this.id_ticket = id_ticket;
        this.gareDepart = gareDepart;
        this.gareArrivee = gareArrivee;
        this.typeTrain = typeTrain;
        this.dateDepart = dateDepart;
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

    public String getGareDepart() {
        return this.gareDepart;
    }

    public void setGareDepart(String gareDepart) {
        this.gareDepart = gareDepart;
    }

    public String getGareArrivee() {
        return gareArrivee;
    }

    public void setGareArrivee(String gareArrivee) {
        this.gareArrivee = gareArrivee;
    }
    public String getTypeTrain() {
        return typeTrain;
    }

    public void setTypeTrain(String typeTrain) {
        this.typeTrain = typeTrain;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

}
