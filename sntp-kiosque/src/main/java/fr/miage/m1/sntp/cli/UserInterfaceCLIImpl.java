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


@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {

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
    public ReservationDTO getReservation(ReservationDTO reservationDTO) throws Exception {
        Integer idReservation = 0;
        if (reservationDTO != null) {
            idReservation = reservationDTO.getId();
            terminal.println(reservationDTO.toString());
        }
        boolean shouldRestart = true;
        while (shouldRestart) {

            shouldRestart = false;
            Collection<GareDTO> gares;
            try {
                gares = gareService.getGares();
            } catch (Exception e) {
                throw new Exception("Infocentre non joignable");
            }
            String villeDepart = textIO.newStringInputReader().withPossibleValues(gares.stream().map(GareDTO::getNomGare).collect(Collectors.toList())).read("Selectionner ville de départ");
            String villeArrivee = textIO.newStringInputReader().withPossibleValues(gares.stream().map(GareDTO::getNomGare).collect(Collectors.toList())).read("Selectionner ville d'arrivée");
            boolean datetimeValid = false;
            Date dateSelected;
            String date = "";
            String time = "";
            do {
                try {
                    date = textIO.newStringInputReader().read("Selectionner date de départ (format: dd/MM/yyyy)");
                    time = textIO.newStringInputReader().read("Selectionner heure de départ (format: HH:mm)");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    dateSelected = sdf.parse(date + " " + time);
                    if (dateSelected.before(new Date())) {
                        throw new Exception("Date de départ antérieure à la date actuelle");
                    }
                    datetimeValid = true;
                } catch (ParseException e) {
                    terminal.println("Date invalide ! (format: dd/MM/yyyy HH:mm)");
                } catch (Exception e) {
                    terminal.println("Date antérieure à la date actuelle !");
                }
            } while (!datetimeValid);
            date = date.replace("/", "-");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf2.format(sdf.parse(date));
            String email;
            if (idReservation == 0) {
                email = getCustomerEmail();

                if (voyageurService.getVoyageurByEmail(email)) {
                    terminal.println("Vous avez déjà un compte chez nous !");
                } else {
                    String prenom = getCustomerFirstName();
                    String nom = getCustomerLastName();
                    voyageurService.createVoyageur(nom, prenom, email);
                }
            } else {
                email = reservationService.getReservation(Long.valueOf(idReservation.toString())).getVoyageur().getEmail();
            }

            ReservationDTO reservation = kiosqueService.getVoyages(villeDepart, villeArrivee, time, date.replace("/", "-"), email, idReservation);

            if (reservation == null) {
                terminal.println("Aucun voyage trouvé pour cette date");
                shouldRestart = true;
                continue;
            }
            terminal.println("Voici votre voyage : ");
            terminal.println("Numéro de reservation : " + reservation.getId() + " à garder precieusement !");
            LocalDate dateResa = reservation.getDateDeReservation();
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            DateFormat outputFormat = new SimpleDateFormat("EEEE dd MMMMM yyyy", Locale.FRENCH);
            Date dateInFrenchFormat = inputFormat.parse(String.valueOf(dateResa));
            String dateInFrenchFormatString = outputFormat.format(dateInFrenchFormat).toLowerCase(Locale.ROOT);
            terminal.println("Date de reservation : " + dateInFrenchFormatString);

            if (idReservation != 0) {
                terminal.println("Supplément : " + reservation.getPrix() + " euros");
            } else {
                terminal.println("Prix payé : " + reservation.getPrix() + " euros");
            }
            reservation.setTickets(reservation.getTickets().stream().sorted(Comparator.comparing(TicketDTO::getNumeroEtape)).collect(Collectors.toList()));
            for (TicketDTO ticket : reservation.getTickets()) {
                terminal.println("Etape n°" + ticket.getNumeroEtape());
                terminal.println("Le chemin ce fera avec le train " + ticket.getNumeroTrain());
                terminal.println("Départ de la gare " + ticket.getGareDepart() + " à " + ticket.getHeureDepart());
                terminal.println("Arrivée à la gare " + ticket.getGareArrivee() + " à " + ticket.getHeureArrivee());
                if (ticket.isReservable()) {
                    terminal.println("Vous serez à la place " + ticket.getPlace());
                } else {
                    terminal.println("C'est un train sans reservation. Assayer vous où vous le souhaitez !");
                }
            }
        }

        return null;
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
        return this.textIO.newStringInputReader().read("Prénom : ");

    }

    @Override
    public String getCustomerLastName() {
        return this.textIO.newStringInputReader().read("Nom : ");

    }

    @Override
    public String getCustomerEmail() {
        return this.textIO.newStringInputReader().read("Email : ");

    }

    @Override
    public void getEchangerBillet() throws Exception {
        Long id = textIO.newLongInputReader().read("Saisir votre numéro de reservation :");
        ReservationDTO reservationDTO = reservationService.getReservation(id);
        if (reservationDTO == null) {
            showErrorMessage("Reservation introuvable !");
        } else {
            getReservation(reservationDTO);
        }

    }


}

