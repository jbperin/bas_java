package com.jibepe.ihmitf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by tbpk7658 on 21/11/2016.
 */
public class ListSelectable <E extends SelectableChoice, F> {

    private List<E> listOfItem= new ArrayList<E> ();
    private ListProvider<F> leListGetter;
    private Class tgt_cls;

    private void refreshList(){
        listOfItem.clear();
        Iterator<F> lit  = leListGetter.getIterator();
        while (lit.hasNext()) {
            F lelem = lit.next();
            try {
                Class[] cArg = new Class[1];
                cArg[0] = lelem.getClass();
                Constructor ct = this.tgt_cls.getDeclaredConstructor(cArg);
                if (!ct.isAccessible())
                    ct.setAccessible(true);
                E newElem = (E)ct.newInstance(lelem);
                listOfItem.add(newElem);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        /*Iterator it = lStream.iterator();
        while (it.hasNext()) {
            F lelem = (F)it.next();
        }*/
    }
    public ListSelectable (ListProvider <F> laListe, Class<E> tgt_cls){
        this.leListGetter =  laListe;
        this.tgt_cls = tgt_cls;
        refreshList();

    }

    public String getElem(int index) {
        refreshList();
        return (((E)(listOfItem.get(index))).getDescription());
    }

    public void selectOne(int index, String command){
        listOfItem.get(index).select(command);
        refreshList();

    }
    @Override
    public String toString() {
        String res="";
        refreshList();
        int nb_elem = listOfItem.size();

        for (int ii=0; ii<nb_elem; ii++){
            res += String.valueOf(ii) + " - " + listOfItem.get(ii).getDescription() + "\n";
        }

        return res;
    }
}
