/*
 * Copyright (c) 2021. Nathan Hayoun
 */

package fr.miage.m1.sntp.models;

public enum TypeDeTrain {
    TGV(true), TER(false);

    private boolean aReservation;

    TypeDeTrain(boolean aReservation) {
        this.aReservation = aReservation;
    }

    public boolean isaReservation() {
        return aReservation;
    }

    public void setaReservation(boolean aReservation) {
        this.aReservation = aReservation;
    }
}
