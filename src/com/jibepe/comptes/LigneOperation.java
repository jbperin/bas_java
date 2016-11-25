package com.jibepe.comptes;


import java.time.LocalDate;
import java.util.Date;

/**
 * Created by tbpk7658 on 14/11/2016.
 */
public class LigneOperation implements java.io.Serializable{

    private String description;
    private double montant;
    private LocalDate date;
    private boolean checked;
    private Compte owner;

    public LigneOperation(String description, double montant , LocalDate date) {
        this.description = description;
        this.montant = montant;
        this.date = date;
        this.owner = null;
    }
    public void check (){
        this.checked = true;
    }
    public boolean isChecked (){
        return (this.checked);
    }

    public double lireMontant() {
        return (this.montant);
    }

    @Override
    public String toString () {
        String res = "";
        if (checked == true){
            res += "x ";
        }else{ res += "  " ;}
        res  = res +  String.valueOf(this.montant) + "\t" +this.description + " created on " + this.date+ " " ;
        return res;
    }

    public void setOwner(Compte compte) {
        this.owner = compte;
    }
    public Compte getOwner() {
        return (this.owner);
    }
}
