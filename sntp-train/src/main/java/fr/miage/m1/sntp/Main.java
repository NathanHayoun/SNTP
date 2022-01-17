package fr.miage.m1.sntp;

import fr.miage.m1.sntp.application.Train;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main {
    public static void main(String[] args) {
        Quarkus.run(Train.class, args);
    }
}
