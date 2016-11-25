package com.jibepe.comptes;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;


/**
 * Created by tbpk7658 on 14/11/2016.
 */
public class Compte implements java.io.Serializable{
    private String nom;
    private double soldeInitial;
    private LocalDate dateCreation;
    private List<LigneOperation> list_Ligne_Operation = null;

    public Compte (String nom, double soldeInitial, LocalDate dateCreation ) {
        this.nom = nom;
        this.soldeInitial = soldeInitial;
        this.dateCreation = dateCreation;
        list_Ligne_Operation = new ArrayList<LigneOperation>();
    }

    public void suppOperation (LigneOperation op) {
        list_Ligne_Operation.remove(op);
    }
    public void ajoutOperation (LigneOperation op) {
        if (op != null) {
            op.setOwner(this);
            this.list_Ligne_Operation.add(op);
        }
    }

    public double lireSoldeCourant (){
        double solde = this.soldeInitial;
        for (LigneOperation ope: list_Ligne_Operation) {
            solde = solde + ope.lireMontant();
        }
        return solde;
    }
    @Override
    public String toString () {
        String res = "";
        res  = res + this.nom + " created on " + this.dateCreation+ " contains " + this.lireSoldeCourant();
        for (LigneOperation lope: list_Ligne_Operation
             ) {
            res += "\n " + lope;
        }
        return res;
    }


    public Stream<LigneOperation> uncheckedOperation() {
        return (list_Ligne_Operation.stream().filter(p -> p.isChecked() == false));
    }
}
