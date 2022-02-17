package fr.miage.m1.sntp.exceptions;

public class TicketException extends Throwable {
    public static class NoTicketException extends Throwable {
        public NoTicketException(int idTicket) {
            super("Aucun ticket trouvé avec l'id " + idTicket);
        }
    }
}
