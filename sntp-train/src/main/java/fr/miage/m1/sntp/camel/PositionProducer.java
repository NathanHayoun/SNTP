package fr.miage.m1.sntp.camel;

import fr.miage.m1.sntp.driver.Driver;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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

/**
 * @author Etan Guir
 */
@ApplicationScoped
public class PositionProducer implements Runnable {
    public static final long INITIAL_DELAY = 10L;
    public static final long PERIOD = 5L;
    private static final String QUEUE_TRAIN_POSITION = "%s/queue/train/position";
    private static final String ERROR_DURING_CNX_FACTORY = "Erreur lors de la création de la connexion factory";
    private static final Logger logger = LoggerFactory.getLogger(PositionProducer.class);
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "fr.miage.m1.sntp.jmsPrefix", defaultValue = "SNTP")
    String prefix;

    private Driver driver;

    private static String toXML(Driver driver) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(driver.getClass());
            StringWriter writer = new StringWriter();
            context.createMarshaller().marshal(driver, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new JAXBException(e);
        }
    }

    void onStart(@Observes StartupEvent ev) {
        try {
            connectionFactory.createConnection();
        } catch (Exception e) {
            logger.warn(ERROR_DURING_CNX_FACTORY, e);
        }
        scheduler.scheduleAtFixedRate(this, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
        driver = new Driver();
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            driver.updateValue();
            Message message = null;
            try {
                message = context.createTextMessage(toXML(driver));
                context.createProducer().send(context.createQueue(String.format(QUEUE_TRAIN_POSITION, prefix)), message);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }
}
