package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.driver.Driver;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Session;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class PositionProducer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PositionProducer.class);
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);
    @Inject
    ConnectionFactory connectionFactory;
    private Driver driver;

    private static String toXML(Driver driver) {
        try {
            JAXBContext context = JAXBContext.newInstance(driver.getClass());
            StringWriter writer = new StringWriter();
            context.createMarshaller().marshal(driver, writer);
            logger.info(writer.toString());
            return writer.toString();
        } catch (JAXBException e) {
            logger.warn("Error when converting XML ", e);
            throw new RuntimeException(e);
        }
    }

    void onStart(@Observes StartupEvent ev) {
        try {
            connectionFactory.createConnection();
        } catch (Exception e) {
            logger.warn("EX", e);
        }
        //on planifie l'exécution de la méthode run() de cette classe:
        // - 10s (initialDelay=10
        // - toute les 5s (period = 5L, unit = secondes)
        scheduler.scheduleAtFixedRate(this, 10L, 5L, TimeUnit.SECONDS);
        driver = new Driver();
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {

        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            //on crée un producteur et on y envoie un message dans une nouvelle queue "prices"
            driver.updateValue();
            Message message = context.createTextMessage(toXML(driver));
            context.createProducer().send(context.createQueue("queue/train/position"), message);
        }
    }
}
