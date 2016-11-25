package com.jibepe.comptes.debiteurs;

/**
 * Created by tbpk7658 on 22/11/2016.
 */

import com.jibepe.comptes.IDebiteur;
import com.jibepe.comptes.LigneOperation;

import java.time.LocalDate;

public abstract class Contrat implements IDebiteur{

    protected String libelle;
    protected double montant;

    public Contrat (String libelle, double montant) {
        this.libelle = libelle;
        this.montant = montant;
    }

    public abstract LigneOperation lireOperation(LocalDate dat);
}
