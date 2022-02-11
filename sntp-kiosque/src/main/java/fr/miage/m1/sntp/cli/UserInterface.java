package fr.miage.m1.sntp.cli;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    String getCustomerFirstName();

    String getCustomerLastName();

    String getCustomerEmail();

    void getEchangerBillet() throws Exception;
}
