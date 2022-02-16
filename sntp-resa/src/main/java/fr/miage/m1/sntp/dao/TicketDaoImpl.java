package fr.miage.m1.sntp.dao;

import fr.miage.m1.sntp.models.Reservation;
import fr.miage.m1.sntp.models.Ticket;
import fr.miage.m1.sntp.models.Voyageur;
import fr.miage.m1.sntp.utils.LibSql;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TicketDaoImpl implements TicketDao {
    private static final String NUMERO_DE_TRAIN = "numeroDeTrain";
    private static final String COUNT_NB_TICKET_PAR_TRAIN_AND_NOW = "select count(*) from Ticket where date_depart = CURRENT_DATE and numero_train = :" + NUMERO_DE_TRAIN;
    private static final String COUNT_NB_TICKET_PAR_TRAIN_AND_NOW_AND_HAS_CORRESPONDANCE = "select count(*) from Ticket where date_depart = CURRENT_DATE and numero_train = :" + NUMERO_DE_TRAIN + " and numeroEtape NOT IN (SELECT max(numeroEtape) FROM Ticket WHERE date_depart = CURRENT_DATE and numero_train = :" + NUMERO_DE_TRAIN + " )";
    private static final String GET_EMAIL_BY_RESERVATION_AND_TRAIN_TODAY = "select v from Ticket t JOIN t.reservationConcernee r JOIN r.voyageurConcernee v where t.dateDepart = CURRENT_DATE and t.numeroTrain = :" + NUMERO_DE_TRAIN;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Ticket> findAll() {
        return LibSql.findAll(entityManager, Ticket.class);
    }


    @Override
    @Transactional
    public Ticket findById(long id) {
        return LibSql.findObject(entityManager, Ticket.class, id);
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        LibSql.insertObject(entityManager, ticket);
    }

    @Override
    @Transactional
    public void update(Ticket ticket) {
        LibSql.update(entityManager, ticket);
    }

    @Override
    @Transactional
    public void delete(Ticket ticket) {
        LibSql.deleteObject(entityManager, ticket);
    }

    @Override
    public Long countNbTicketByNumeroTrainAndNow(int numeroDeTrain) {
        Map<String, Object> params = new HashMap<>();
        params.put(NUMERO_DE_TRAIN, numeroDeTrain);
        return (Long) LibSql.executeSelectWithNamedParams(entityManager, Long.class, COUNT_NB_TICKET_PAR_TRAIN_AND_NOW, params).get(0);
    }

    @Override
    public Long countNbTicketByNumeroTrainAndNowAndHasEtape(int numeroDeTrain) {
        Map<String, Object> params = new HashMap<>();
        params.put(NUMERO_DE_TRAIN, numeroDeTrain);
        return (Long) LibSql.executeSelectWithNamedParams(entityManager, Long.class, COUNT_NB_TICKET_PAR_TRAIN_AND_NOW_AND_HAS_CORRESPONDANCE, params).get(0);
    }

    @Override
    public List<Voyageur> getEmailsByTrainAndDate(int numeroDeTrain) {
        Map<String, Object> params = new HashMap<>();
        params.put(NUMERO_DE_TRAIN, numeroDeTrain);

        return LibSql.executeSelectWithNamedParams(entityManager, Voyageur.class, GET_EMAIL_BY_RESERVATION_AND_TRAIN_TODAY, params);
    }

    @Override
    @Transactional
    public Reservation emitTicketForCustomer(Long ticketId, Voyageur voyageur, Reservation reservation) {
        Ticket t = LibSql.findObject(entityManager, Ticket.class, ticketId);
        Reservation r = LibSql.findObject(entityManager, Reservation.class, reservation.getId());
        r.setVoyageur(voyageur);
        var listeTickets = r.getTickets();
        listeTickets.add(t);
        r.setTickets(listeTickets);
        return r;
    }

}
