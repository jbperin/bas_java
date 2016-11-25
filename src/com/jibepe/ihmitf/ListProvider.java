package com.jibepe.ihmitf;

import java.util.Iterator;

/**
 * Created by tbpk7658 on 21/11/2016.
 */
public interface ListProvider <E>{
    public Iterator<E> getIterator();
}
