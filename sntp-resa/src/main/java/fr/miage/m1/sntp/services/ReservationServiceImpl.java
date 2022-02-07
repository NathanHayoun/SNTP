package fr.miage.m1.sntp.services;

import fr.miage.m1.sntp.dao.ReservationDao;
import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.dto.TicketDTO;
import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    EntityManager em;

    @Inject
    ReservationDao reservationDao;

    @Override
    @Transactional
    public ReservationDTO reserver(ReservationDTO reservation) {
        Reservation reservationEntity = new Reservation();

        Voyageur voyageur = new Voyageur();
        // TODO: make api for searching acheteur id call instead ...
        voyageur.setNom(reservation.getAcheteur().getNom());
        voyageur.setPrenom(reservation.getAcheteur().getPrenom());
        voyageur.setEmail(reservation.getAcheteur().getEmail());
        // TODO: if acheteur is not found, create it

        reservationEntity.setVoyageur(voyageur);

        List<TicketDTO> tickets = new ArrayList<>(reservation.getTickets());

        reservationEntity.setDateDeReservation(LocalDate.parse(tickets.get(0).getDateDepart().substring(0, 10)));

        for (TicketDTO ticket : tickets) {
            Ticket ticketEntity = new Ticket();
            ticketEntity.setGareDepart(ticket.getGareDepart());
            ticketEntity.setGareArrivee(ticket.getGareArrivee());
            // TODO: ajouter tout les autres attributs
            reservationEntity.addTicket(ticketEntity);
        }

        reservationDao.save(reservationEntity);
        reservation.setId(reservationEntity.getId());
        return reservation;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationDao.findById(id);
    }
}
