package com.jibepe.comptes;

import java.time.LocalDate;


/**
 * Created by tbpk7658 on 22/11/2016.
 */
public interface IDebiteur {

    LigneOperation lireOperation (LocalDate dat);

}
