package com.pyyne.ports;

import com.pyyne.entities.PyyneControllerAccountBallance;
import com.pyyne.entities.PyyneControllerTransaction;

public interface ReporterPort {

    public String formatTransaction(PyyneControllerTransaction transaction);

    public String formatBallance(PyyneControllerAccountBallance transactions);

}
