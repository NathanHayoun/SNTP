package fr.miage.m1.sntp.cli;


import fr.miage.m1.sntp.dto.ReservationDTO;
import fr.miage.m1.sntp.dto.TicketDTO;
import fr.miage.m1.sntp.dto.VoyageDTO;
import fr.miage.m1.sntp.resources.KiosqueService;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@ApplicationScoped
public class UserInterfaceCLIImpl implements UserInterfaceCLI {

    @Inject
    @RestClient
    KiosqueService kiosqueService;

    TextTerminal<?> terminal;
    TextIO textIO;

    @ConfigProperty(name = "fr.miage.m1.sntp.kiosqueId")
    Integer kiosqueId;

    @Override
    public ReservationDTO getReservation() {
        String villeDepart = textIO.newStringInputReader().read("Selectionner ville de départ");
        String villeArrivee = textIO.newStringInputReader().read("Selectionner ville d'arrivée");
        boolean datetimeValid = false;
        Date dateSelected;
        String date = "";
        String time = "";
        do {
            try {
                date = textIO.newStringInputReader().read("Selectionner date de départ (format: dd/MM/yyyy)");
                time = textIO.newStringInputReader().read("Selectionner date de départ (format: HH:mm)");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dateSelected = sdf.parse(date + " " + time);
                if (dateSelected.before(new Date())) {
                    throw new Exception("Date de départ antérieure à la date actuelle");
                }
                datetimeValid = true;
            } catch (ParseException e) {
                terminal.println("Date invalide ! (format: yyyy-MM-dd HH:mm)");
            } catch (Exception e) {
                terminal.println("Date antérieure à la date actuelle !");
            }
        } while (!datetimeValid);

        Collection<VoyageDTO> voyages = kiosqueService.getVoyages(villeDepart, villeArrivee, date.replace("/", "-") + ":" + time);

        // TODO: check if there is a voyage with the selected date

        int i = 1;
        for (VoyageDTO voyage : voyages) {
            ArrayList<TicketDTO> tickets = new ArrayList<TicketDTO>(voyage.getTickets());
            terminal.println("[" + i + "] " + tickets.get(0).getVilleDepart() + " --> " + tickets.get(tickets.size() - 1).getVilleArrivee());
            for (TicketDTO ticket : tickets) {
                terminal.println(ticket.getTypeTrain() + " : " + ticket.getVilleDepart() + " --> " + ticket.getVilleArrivee());
            }
            i++;
        }

        int index = textIO.newIntInputReader().withMinVal(1).withMaxVal(voyages.size()).read("Selectionner un trajet");
        VoyageDTO selectedVoyage = (VoyageDTO) voyages.toArray()[index - 1];

        Integer sittingCount = textIO.newIntInputReader().withMinVal(1).withMaxVal(selectedVoyage.getNbMaxTickets()).read("Pour combien de personne ? (max: " + selectedVoyage.getNbMaxTickets() + ")");

        return new ReservationDTO();
    }

//    public void displayAvailableGigsToCli(){
//        terminal.println("KiosqueId="+ kiosqueId);
//        Collection<VoyageDTO> voyages = kiosqueService.getVoyages(kiosqueId);
//        for (Voyage voyage : ) {
//            terminal.println("[" + gig.getVenueId() + "] " + gig.getArtistName() + " " + gig.getDate().format(DateTimeFormatter.ISO_DATE) + " " + gig.getLocation());
//        }
//    }
//
//    public Booking getBookingFromOperator(){
//        terminal.println("Which Gig to book?");
//
//        Integer venueId = textIO.newIntInputReader().withPossibleValues(vendorService.getGigs(kiosqueId).stream().map(g -> g.getVenueId()).collect(Collectors.toList())).read("Which venue?");
//        Integer sittingCount = textIO.newIntInputReader().read("How many seats?");
//        Integer standingCount = textIO.newIntInputReader().read("How many pit tickets?");
//
//        return new Booking(kiosqueId,venueId,standingCount,sittingCount);
//    }

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
        return this.textIO.newStringInputReader().read("Customer First Name");

    }

    @Override
    public String getCustomerLastName() {
        return this.textIO.newStringInputReader().read("Customer Last Name");

    }

    @Override
    public String getCustomerEmail() {
        return this.textIO.newStringInputReader().read("Customer Email");

    }


}

