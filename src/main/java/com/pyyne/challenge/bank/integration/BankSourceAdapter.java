package com.pyyne.challenge.bank.integration;

import com.pyyne.entities.PyyneBankAccountMetadata;
import com.pyyne.entities.PyyneControllerAccountBallance;
import com.pyyne.entities.PyyneControllerTransaction;
import com.pyyne.ports.BankSourcesPort;

import java.util.*;

public class BankSourceAdapter implements BankSourcesPort {

    private Map<String, BankSourcesPort> sourceMap = new HashMap<>();
    public BankSourceAdapter(){
        this.sourceMap.put("bank1", new Bank1Source());
        this.sourceMap.put("bank2", new Bank2Source());
    }


    @Override
    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata, Date from, Date to) {
        return this.sourceMap.get(accountMetadata.getSource()).getTransactions(accountMetadata,from,to);
    }

    @Override
    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata) {
        return this.sourceMap.get(accountMetadata.getSource()).getTransactions(accountMetadata);
    }

    @Override
    public PyyneControllerAccountBallance getGetBalance(PyyneBankAccountMetadata accountMetadata) {
        return this.sourceMap.get(accountMetadata.getSource()).getGetBalance(accountMetadata);
    }
}





