package fr.miage.m1.sntp.tasks;

import fr.miage.m1.sntp.application.InfoCentreMetier;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import java.util.TimerTask;

@ApplicationScoped
public class GeneratePassageTask extends TimerTask {
    @Inject
    InfoCentreMetier infoCentreMetier;

    /**
     * The action to be performed by this timer task.
     */
    @Override
    @ActivateRequestContext
    public void run() {
        infoCentreMetier.genererPassages();
    }
}
