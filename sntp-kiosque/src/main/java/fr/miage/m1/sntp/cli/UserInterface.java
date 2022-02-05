package fr.miage.m1.sntp.cli;

import fr.miage.m1.sntp.dto.ReservationDTO;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    String getCustomerFirstName();

    String getCustomerLastName();

    String getCustomerEmail();

    ReservationDTO getReservation();
}
