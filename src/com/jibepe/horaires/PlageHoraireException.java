package com.jibepe.horaires;

/**
 * Created by tbpk7658 on 07/11/2016.
 */
public class PlageHoraireException extends Exception {
    public PlageHoraireException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "PlageHoraireException";
    }
}
