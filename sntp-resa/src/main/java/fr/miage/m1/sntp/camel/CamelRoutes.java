package fr.miage.m1.sntp.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
    }
//
//
//    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
//    String jmsPrefix;
//
////    @Inject
////    BookingGateway bookingHandler;
//
////    @Inject
////    TicketingService ticketingService;
//
//    @Inject
//    CamelContext camelContext;
//
//    @Override
//    public void configure() throws Exception {
//
//        camelContext.setTracing(true);
//
////        onException(ExpiredTransitionalTicketException.class)
////                .handled(true)
////                .process(new ExpiredTransitionalTicketProcessor())
////                .setHeader("success", simple("false"))
////                .log("Clearning expired transitional ticket ${body}")
////                .bean(ticketingService, "cleanUpTransitionalTicket");
////
////        onException(UnsuficientQuotaForVenueException.class)
////                .handled(true)
////                .setHeader("success", simple("false"))
////                .setBody(simple("Vendor has not enough quota for this venue"));
////
////
////        onException(NoSuchTicketException.class)
////                .handled(true)
////                .setHeader("success", simple("false"))
////                .setBody(simple("Ticket has expired"));
////
////        onException(CustomerNotFoundException.NoSeatAvailableException.class)
////                .handled(true)
////                .setHeader("success", simple("false"))
////                .setBody(simple("No seat is available"));
//
//
//        from("jms:" + jmsPrefix + "booking?exchangePattern=InOut")
//                .log("ticker received: ${in.headers}")
//                .unmarshal().json(Booking.class)
//                .bean(bookingHandler, "book").marshal().json()
//        ;
//
//
//        from("jms:" + jmsPrefix + "ticket?exchangePattern=InOut")
//                .unmarshal().json(ETicket.class)
//                .bean(ticketingService, "emitTicket").marshal().json();
//
//
//        from("direct:ticketCancel")
//                .marshal().json()
//                .to("jms:topic:" + jmsPrefix + "cancellation");
//
//    }
//
//    private static class ExpiredTransitionalTicketProcessor implements Processor {
//        @Override
//        public void process(Exchange exchange) throws Exception {
//            //https://camel.apache.org/manual/exception-clause.html
//            CamelExecutionException caused = (CamelExecutionException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
//
//
//            exchange.getMessage().setBody(((ExpiredTransitionalTicketException) caused.getCause()).getExpiredTicketId());
//        }
//    }
}
