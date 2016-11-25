package com.jibepe.comptes.debiteurs;

import com.jibepe.comptes.LigneOperation;

import java.time.LocalDate;

/**
 * Created by tbpk7658 on 22/11/2016.
 */
public class ContratMensuel extends Contrat {

    private int dayOfMonth;

    public ContratMensuel (String libelle, double montant, int dayOfMonth) {
        super (libelle, montant);
        this.dayOfMonth = dayOfMonth;
    }
    @Override
    public LigneOperation lireOperation(LocalDate dat) {
        if (dat.getDayOfMonth() == this.dayOfMonth) {
            return (new LigneOperation(this.libelle, this.montant, dat));
        }
        return null;
    }
}
