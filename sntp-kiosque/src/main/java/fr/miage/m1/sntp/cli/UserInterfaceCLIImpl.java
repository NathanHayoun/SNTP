package fr.miage.m1.sntp.cli;

import fr.miage.m1.sntp.dto.GareDTO;
import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.dto.TicketDTO;
import fr.miage.m1.sntp.services.GareService;
import fr.miage.m1.sntp.services.KiosqueService;
import fr.miage.m1.sntp.services.ReservationService;
import fr.miage.m1.sntp.services.VoyageurService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jgroups.util.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Quentin Vaillant
 */
@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {

    private static final String VOYAGEUR = "Voyageur : %s ";
    private static final String DATE_DE_VOYAGE = "Date de voyage : %s ";
    private static final String GARE_DE_DEPART = "Gare de départ : %s";
    private static final String GARE_D_ARRIVEE = "Gare d'arrivée : %s";
    private static final String INFOCENTRE_NON_JOIGNABLE = "Infocentre non joignable";
    private static final String SELECTION_DATE = "Sélectionner date de départ (format: dd/MM/yyyy)";
    private static final String SELECTION_HEURE = "Sélectionner heure de départ (format: HH:mm)";
    private static final String DATE_FORMAT_FOR_CAST_DATE = "dd/MM/yyyy HH:mm";
    private static final String DATE_DEPART_INFERIEUR = "Date de départ antérieure à la date actuelle";
    private static final String DATE_INVALIDE = "Date invalide ! (format: dd/MM/yyyy HH:mm)";
    private static final String COMPTE_DEJA_CREER = "Vous avez déjà un compte chez nous !";
    private static final String PATTERN_ENGLISH_DATE = "yyyy-MM-dd";
    private static final String PATTERN_FRENCH_DATE = "EEEE dd MMMMM yyyy";
    private static final String PATTERN_DATE_FRENCH_LITTlE = "dd-MM-yyyy";
    private static final String SAISIR_NUMERO_RESERVATION = "Saisir votre numéro de reservation :";
    private static final String RESERVATION_INTROUVABLE = "Réservation introuvable !";
    private static final String EMAIL = "Email : ";
    private static final String NOM = "Nom : ";
    private static final String PRENOM = "Prénom : ";
    private static final String TARGET = "/";
    private static final String REPLACEMENT = "-";
    private static final String AUCUN_VOYAGE_TROUVE = "Aucun voyage trouvé pour cette date";
    private static final String VOICI_VOTRE_VOYAGE = "Réservation effectuée ! \n Voici votre voyage : ";
    private static final String AFFICHAGE_NUMERO_DE_RESERVATION = "Numéro de reservation : %s à garder précieusement !";
    private static final String DATE_DE_RESERVATION = "Date de réservation : %s ";
    private static final String SUPPLEMENT = "Supplément : %s euros ";
    private static final String PRIX_PAYE = "Prix payé : %s euros ";
    private static final String NUMERO_ETAPE = "Étape n° %s";
    private static final String CHEMIN_AVEC_TRAIN = "Le chemin se fera avec le train %s ";
    private static final String DEPART_DE_GARE_PLUS_HORRAIRE = "Départ de la gare %s à %s";
    private static final String ARRIVEE_A_LA_GARE_PLUS_HORRAIRE = "Arrivée à la gare %s à %s ";
    private static final String NUMERO_DE_PLACE = "Vous serez à la place %s";
    private static final String TRAIN_SANS_RESA_PLAEC = "C'est un train sans réservation. Asseyez-vous où vous le souhaitez !";
    private static final String RESERVATION_TROUVE = "Réservation trouvée !";

    @Inject
    @RestClient
    KiosqueService kiosqueService;

    @Inject
    @RestClient
    GareService gareService;

    @Inject
    @RestClient
    VoyageurService voyageurService;

    @Inject
    @RestClient
    ReservationService reservationService;

    TextTerminal<?> terminal;
    TextIO textIO;

    @Override
    public void getReservation(ReservationDTO reservationDTO) throws Exception {
        int idReservation = 0;

        if (reservationDTO != null) {
            idReservation = reservationDTO.getId();
            printReservationExisted(reservationDTO);
        }
        boolean shouldRestart = true;

        while (shouldRestart) {
            shouldRestart = false;
            Collection<GareDTO> gares = null;

            try {
                gares = gareService.getGares();
            } catch (Exception e) {
                showErrorMessage(INFOCENTRE_NON_JOIGNABLE);
            }
            assert gares != null;
            String villeDepart = textIO.newStringInputReader().withPossibleValues(gares.stream().map(GareDTO::getNomGare).collect(Collectors.toList())).read("Selectionner ville de départ");
            String villeArrivee = textIO.newStringInputReader().withPossibleValues(gares.stream().map(GareDTO::getNomGare).collect(Collectors.toList())).read("Selectionner ville d'arrivée");
            Tuple<String, String> dateTime = getDateTimeFromUser();
            String date = transformDateInEnglishFormat(dateTime.getVal1());
            String time = dateTime.getVal2();
            String email = getEmailOrCreateNewAccount(idReservation);
            ReservationDTO reservation = kiosqueService.getVoyages(villeDepart, villeArrivee, time, date.replace(TARGET, REPLACEMENT), email, idReservation);

            if (reservation.getTickets().size() == 0) {
                showErrorMessage(AUCUN_VOYAGE_TROUVE);
                shouldRestart = true;
                continue;
            }
            afficherVoyage(idReservation, reservation);
        }
    }

    private void afficherVoyage(int idReservation, ReservationDTO reservation) throws ParseException {
        showSuccessMessage(VOICI_VOTRE_VOYAGE);
        terminal.println(String.format(AFFICHAGE_NUMERO_DE_RESERVATION, reservation.getId()));
        LocalDate dateResa = reservation.getDateDeReservation();
        terminal.println(String.format(DATE_DE_RESERVATION, formatDateInFrench(dateResa)));

        if (idReservation != 0) {
            terminal.println(String.format(SUPPLEMENT, reservation.getPrix()));
        } else {
            terminal.println(String.format(PRIX_PAYE, reservation.getPrix()));
        }
        reservation.setTickets(reservation.getTickets().stream().sorted(Comparator.comparing(TicketDTO::getNumeroEtape)).collect(Collectors.toList()));

        for (TicketDTO ticket : reservation.getTickets()) {
            terminal.println(String.format(NUMERO_ETAPE, ticket.getNumeroEtape()));
            terminal.println(String.format(CHEMIN_AVEC_TRAIN, ticket.getNumeroTrain()));
            terminal.println(String.format(DEPART_DE_GARE_PLUS_HORRAIRE, ticket.getGareDepart(), ticket.getHeureDepart()));
            terminal.println(String.format(ARRIVEE_A_LA_GARE_PLUS_HORRAIRE, ticket.getGareArrivee(), ticket.getHeureArrivee()));

            if (ticket.isReservable()) {
                terminal.println(String.format(NUMERO_DE_PLACE, ticket.getPlace()));
            } else {
                terminal.println(TRAIN_SANS_RESA_PLAEC);
            }
        }
    }

    private String getEmailOrCreateNewAccount(int idReservation) {
        String email;

        if (idReservation == 0) {
            email = getCustomerEmail();

            if (voyageurService.getVoyageurByEmail(email)) {
                terminal.println(COMPTE_DEJA_CREER);
            } else {
                String prenom = getCustomerFirstName();
                String nom = getCustomerLastName();
                voyageurService.createVoyageur(nom, prenom, email);
            }
        } else {
            email = reservationService.getReservation(Long.valueOf(Integer.toString(idReservation))).getVoyageur().getEmail();
        }

        return email;
    }

    private Tuple<String, String> getDateTimeFromUser() {
        boolean datetimeValid = false;
        Date dateSelected;
        String date = "";
        String time = "";
        do {
            try {
                date = textIO.newStringInputReader().read(SELECTION_DATE);
                time = textIO.newStringInputReader().read(SELECTION_HEURE);
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_FOR_CAST_DATE);
                dateSelected = sdf.parse(date + " " + time);
                if (dateSelected.before(new Date())) {
                    showErrorMessage(DATE_DEPART_INFERIEUR);
                } else {
                    datetimeValid = true;
                }
            } catch (ParseException e) {
                showErrorMessage(DATE_INVALIDE);
            }
        } while (!datetimeValid);

        return new Tuple<>(date, time);
    }

    private void printReservationExisted(ReservationDTO reservationDTO) throws ParseException {
        showSuccessMessage(RESERVATION_TROUVE);
        terminal.println(String.format(VOYAGEUR, reservationDTO.getVoyageur().getNom()));
        terminal.println(String.format(DATE_DE_VOYAGE, formatDateInFrench(reservationDTO.getDateDeReservation())));
        terminal.println(String.format(GARE_DE_DEPART, reservationDTO.getTickets().get(0).getGareDepart()));
        terminal.println(String.format(GARE_D_ARRIVEE, reservationDTO.getTickets().get(reservationDTO.getTickets().size() - 1).getGareArrivee()));
    }

    private String formatDateInFrench(LocalDate dateResa) throws ParseException {
        DateFormat inputFormat = new SimpleDateFormat(PATTERN_ENGLISH_DATE, Locale.US);
        DateFormat outputFormat = new SimpleDateFormat(PATTERN_FRENCH_DATE, Locale.FRENCH);
        Date dateInFrenchFormat = inputFormat.parse(String.valueOf(dateResa));

        return outputFormat.format(dateInFrenchFormat).toLowerCase(Locale.ROOT);
    }

    private String transformDateInEnglishFormat(String date) throws ParseException {
        date = date.replace(TARGET, REPLACEMENT);
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_FRENCH_LITTlE);
        SimpleDateFormat sdf2 = new SimpleDateFormat(PATTERN_ENGLISH_DATE);
        date = sdf2.format(sdf.parse(date));

        return date;
    }

    @Override
    public void accept(TextIO textIO, RunnerData runnerData) {
        this.textIO = textIO;
        terminal = textIO.getTextTerminal();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        terminal.getProperties().setPromptColor(Color.RED);
        terminal.println(errorMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public String getCustomerFirstName() {
        return this.textIO.newStringInputReader().read(PRENOM);
    }

    @Override
    public String getCustomerLastName() {
        return this.textIO.newStringInputReader().read(NOM);
    }

    @Override
    public String getCustomerEmail() {
        return this.textIO.newStringInputReader().read(EMAIL);
    }

    @Override
    public void getEchangerBillet() throws Exception {
        Long id = textIO.newLongInputReader().read(SAISIR_NUMERO_RESERVATION);
        ReservationDTO reservationDTO = reservationService.getReservation(id);

        if (reservationDTO == null) {
            showErrorMessage(RESERVATION_INTROUVABLE);
        } else {
            getReservation(reservationDTO);
        }
    }
}

