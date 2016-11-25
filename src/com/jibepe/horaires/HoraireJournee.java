package com.jibepe.horaires;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Collections;
/**
 * Created by tbpk7658 on 07/11/2016.
 */
public class HoraireJournee {

    private List<PlageHoraire> list_PlageHoraire = null;
    private SimpleDateFormat df = new SimpleDateFormat( "ddMMyyyy" );
    private Date aDate;

    public HoraireJournee(String aDay) throws ParseException {
        list_PlageHoraire = new ArrayList<PlageHoraire>();
        aDate = df.parse(aDay);
        System.out.println(aDate);
    }

    public void addHoraire(String horaire_debut, String horaire_fin) throws ParseException, PlageHoraireException {
        PlageHoraire nph = new PlageHoraire(horaire_debut, horaire_fin);
        for (PlageHoraire plg: list_PlageHoraire) {
            if ( plg.collapseWith(nph)) {
                throw new PlageHoraireException ("Overlapping ");
            }
        }

        list_PlageHoraire.add( nph);
        Collections.sort(list_PlageHoraire);
    }

    @Override
    public String toString() {
        String st_list_plage = "";
        for (PlageHoraire plg: list_PlageHoraire) {
            st_list_plage += plg + " " ;
        }
        return "HoraireJournee "+ df.format(aDate) +" " + st_list_plage;
    }
}
