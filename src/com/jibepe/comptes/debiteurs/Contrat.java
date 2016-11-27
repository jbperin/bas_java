package com.jibepe.comptes.debiteurs;

/**
 * Created by tbpk7658 on 22/11/2016.
 */

import com.jibepe.comptes.IDebiteur;
import com.jibepe.comptes.LigneOperation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Contrat implements IDebiteur{

    protected String libelle;
    protected double montant;
    protected LocalDate dateDerniereOperation;

    public LocalDate getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(LocalDate dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    protected LocalDate dateDemarrage = null;
    protected LocalDate dateExpiration = null;

    public Contrat (String libelle, double montant) {
        this.libelle = libelle;
        this.montant = montant;
        this.dateDemarrage = null;
        this.dateExpiration = null;
        this.dateDerniereOperation = null;
    }
    public LigneOperation operationOnDate(LocalDate dat){
        LigneOperation uneLigneOperation = null;
        boolean activeContrat = true;

        if ((this.dateExpiration != null) && (dat.isAfter(this.dateExpiration))) activeContrat = false;
        if ((this.dateDemarrage != null) && (dat.isBefore(this.dateDemarrage))) activeContrat = false;

        if (activeContrat) {
            uneLigneOperation = lireOperation(dat);
        }

        if (uneLigneOperation != null) {
            dateDerniereOperation = dat;
        }
        return uneLigneOperation;
    }
    public abstract LigneOperation lireOperation(LocalDate dat);
}
