package com.pyyne.entities;

public interface PyyneControllerValue {

    public PyyneAllowedCurrency getCurrency();

    public double getAmount();


    // for account metadata
    public PyyneBankAccountMetadata getAccountMetadata();

    public void setAccountMetadata(PyyneBankAccountMetadata metadata);
}
