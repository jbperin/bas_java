package com.jibepe.horaires;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by tbpk7658 on 07/11/2016.
 */
public class PlageHoraire implements Comparable<PlageHoraire>{

    private Date debut, fin;
    private SimpleDateFormat df = new SimpleDateFormat( "HHmm" );

    public PlageHoraire(String deb, String fi) throws ParseException, PlageHoraireException {

        debut = df.parse(deb);
        fin = df.parse(fi);
        if (debut.after(fin)) {
            throw new PlageHoraireException("End time can't precede start time");
        }
    }
    public boolean collapseWith (PlageHoraire ph) {
        return ((ph.debut.before(fin) &&  ph.debut.after(debut)) ||
                (ph.fin.before(fin) &&  ph.fin.after(debut))) ;
    }
    @Override
    public String toString() {
        return df.format(debut) + " " + df.format(fin);
    }

    @Override
    public int compareTo(PlageHoraire o) {
        if (debut.after(o.debut)) {
            return (1);
        } else if (debut.before(o.debut)) {
            return (-1);
        }
        return 0;
    }
}
