package com.pyyne.challenge.bank.entities;

import com.pyyne.entities.PyyneAllowedCurrency;
import com.pyyne.entities.PyyneBankAccountMetadata;
import com.pyyne.entities.PyyneControllerAccountBallance;

public class AccountBallance implements PyyneControllerAccountBallance {


    private double value;
    private PyyneAllowedCurrency currency;
    private PyyneBankAccountMetadata accountMetadata;

    public AccountBallance(PyyneAllowedCurrency currency, double value, PyyneBankAccountMetadata metadata){
        this.currency = currency;
        this.value = value;
        this.setAccountMetadata(metadata);
    }

    @Override
    public PyyneAllowedCurrency getCurrency() {
        return this.currency;
    }

    @Override
    public double getAmount() {
        return this.value;
    }

    @Override
    public PyyneBankAccountMetadata getAccountMetadata() {
        return this.accountMetadata;
    }

    @Override
    public void setAccountMetadata(PyyneBankAccountMetadata metadata) {
        this.accountMetadata = metadata;
    }
}
