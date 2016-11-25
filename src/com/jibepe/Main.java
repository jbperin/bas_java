package com.jibepe;

import com.jibepe.comptes.Compte;
import com.jibepe.comptes.LigneOperation;
import com.jibepe.comptes.debiteurs.Contrat;
import com.jibepe.comptes.debiteurs.ContratAnnuel;
import com.jibepe.comptes.debiteurs.ContratMensuel;
import com.jibepe.horaires.HoraireJournee;
import com.jibepe.horaires.PlageHoraireException;
import com.jibepe.ihmitf.ListProvider;
import com.jibepe.ihmitf.ListSelectable;
import com.jibepe.ihmitf.SelectableChoice;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

class IHMoperation implements SelectableChoice {

    private LigneOperation ope;

    public IHMoperation (LigneOperation ope) {
        this.ope = ope;
    }

    @Override
    public String getDescription() {
        return ope.toString();
    }

    @Override
    public void select(String command) {
        if (command.equalsIgnoreCase("Pointe")) {
            ope.check();
        } else if (command.equalsIgnoreCase("Supprime")){
            ope.getOwner().suppOperation(ope);
        } else {
            System.out.println ("Method not yet implemented " + command);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        useDebiteur();
        //useCompte();
        //useHoraire();
        //useItfIhm ();
        //useSerialize();
        //System.out.println("coucou ph1 = ", lignes);
    }

    private static void useDebiteur() {
        List<Contrat> listeDeContrat = new ArrayList<Contrat> ();
        Contrat ContratAssurance = new ContratMensuel("Assurance", -30.00, 8);
        listeDeContrat.add(ContratAssurance);

        Contrat Impots = new ContratAnnuel("Impot", -120.00, 8, Month.JANUARY);
        listeDeContrat.add(Impots);

        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = dateDebut.plusMonths(4);

        LocalDate dateParcours = dateDebut;
        while (dateParcours.compareTo(dateFin) < 0) {
            for (Contrat cont: listeDeContrat
                 ) {
                LigneOperation ope;
                if ((ope = cont.lireOperation(dateParcours)) != null) {
                    System.out.println(ope);
                }
            }
            dateParcours = dateParcours.plusDays(1);
        }
    }

    private static void useSerialize() {

        Compte compteCourant = new Compte ("Compte Courant CM", 100.00, LocalDate.of(2014, Month.DECEMBER, 12));
        compteCourant.ajoutOperation(new LigneOperation("Retrait DAB", -20.00, LocalDate.of(2014, Month.DECEMBER, 13)));
        compteCourant.ajoutOperation(new LigneOperation("Péage autoroute", -12.00, LocalDate.of(2014, Month.DECEMBER, 14)));

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("CompteCourant.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(compteCourant);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in CompteCourant.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }


        Compte e = null;
        try {
            FileInputStream fileIn = new FileInputStream("CompteCourant.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Compte) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Compte class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Compte = \n"+ e);

    }


    private static void useItfIhm () {
        Compte compteCourant = new Compte ("Compte Courant CM", 100.00, LocalDate.of(2014, Month.DECEMBER, 12));


        ListSelectable <IHMoperation, LigneOperation> lignes = new ListSelectable<IHMoperation, LigneOperation>(
                new ListProvider<LigneOperation>() {
                    public Iterator<LigneOperation> getIterator() {
                        return compteCourant.uncheckedOperation().iterator();
                    }
                } , IHMoperation.class);



        LigneOperation uneLigneOperation = new LigneOperation("Retrait DAB", -20.00, LocalDate.of(2014, Month.DECEMBER, 13));
        compteCourant.ajoutOperation(uneLigneOperation);

        LigneOperation uneAutreLigneOperation = new LigneOperation("Péage autoroute", -12.00, LocalDate.of(2014, Month.DECEMBER, 14));
        compteCourant.ajoutOperation(uneAutreLigneOperation);



        //uneAutreLigneOperation.check();
        System.out.println("##---------------##\n");
        System.out.println("Compte = \n"+ compteCourant);
        System.out.println("Liste des opérations: \n" + lignes + "FIN de liste");

        System.out.println("Pointage d'une opération\n");
        lignes.selectOne(1, "Pointe");

        System.out.println("##---------------##\n");
        System.out.println("Compte = \n"+ compteCourant);
        System.out.println("Liste des opérations: \n" + lignes + "FIN de liste");

        System.out.println("Suppression d'une opération\n");
        lignes.selectOne(0, "Supprime");

        System.out.println("##---------------##\n");
        System.out.println("Compte = \n"+ compteCourant);
        System.out.println("Liste des opérations: \n" + lignes + "FIN de liste");


    }


    private static void useCompte() {

        Compte compteCourant = new Compte ("Compte Courant CM", 100.00, LocalDate.of(2014, Month.DECEMBER, 12));

        LigneOperation uneLigneOperation = new LigneOperation("Retrait DAB", -20.00, LocalDate.of(2014, Month.DECEMBER, 13));
        compteCourant.ajoutOperation(uneLigneOperation);
        LigneOperation uneAutreLigneOperation = new LigneOperation("Péage autoroute", -12.00, LocalDate.of(2014, Month.DECEMBER, 14));
        compteCourant.ajoutOperation(uneAutreLigneOperation);
        uneAutreLigneOperation.check();

        System.out.println (compteCourant);

        Iterator it = compteCourant.uncheckedOperation().iterator();
        while (it.hasNext()) {
            Object elem = it.next();
            System.out.println(elem);
        }

/*
        SimpleDateFormat df = new SimpleDateFormat( "ddMMyyyy" );
        df.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        try {
            Compte compteCourant = Compte ("Compte Courant CM", 10.00, df.parse("12122016"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
    }

    private static void useHoraire (){
        String uneDateTexte = "07112016";
        String TimeZoneName = "Europe/Paris";
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat df = new SimpleDateFormat( "ddMMyyyy" );
        try {
            Date res = df.parse("20261991");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(" date = " + df.format(res));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            HoraireJournee hj = new HoraireJournee(uneDateTexte);
            System.out.println(hj);
            hj.addHoraire("1300", "1800");
            hj.addHoraire("0800", "1200");
            System.out.println(hj);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (PlageHoraireException e){
            e.printStackTrace();
        }
    }
}
