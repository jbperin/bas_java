package com.jibepe.comptes.debiteurs;

import com.jibepe.comptes.LigneOperation;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by tbpk7658 on 22/11/2016.
 */
public class ContratAnnuel extends Contrat {

    private int dayOfMonth;
    private Month theMonth;

    public ContratAnnuel(String libelle, double montant, int dayOfMonth, Month month) {
        super(libelle, montant);
        this.dayOfMonth = dayOfMonth;
        this.theMonth=month;
    }

    @Override
    public LigneOperation lireOperation(LocalDate dat) {
        if ((dat.getDayOfMonth() == this.dayOfMonth) && (dat.getMonth() == this.theMonth)){
            return (new LigneOperation(this.libelle, this.montant, dat));
        }
        return null;
    }

    @Override
    public String toString() {
        return "ContratAnnuel{}";
    }
}
