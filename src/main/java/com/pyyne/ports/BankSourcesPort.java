package com.pyyne.ports;

import com.pyyne.entities.PyyneBankAccountMetadata;
import com.pyyne.entities.PyyneControllerAccountBallance;
import com.pyyne.entities.PyyneControllerTransaction;

import java.util.Date;
import java.util.List;

public interface BankSourcesPort {

    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata, Date from, Date to);

    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata);

    public PyyneControllerAccountBallance getGetBalance(PyyneBankAccountMetadata accountMetadata);
}
