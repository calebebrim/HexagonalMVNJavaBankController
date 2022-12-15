package com.pyyne.challenge.bank.entities;

import com.bank1.integration.Bank1Transaction;
import com.pyyne.entities.PyyneAllowedCurrency;
import com.pyyne.entities.PyyneBankAccountMetadata;
import com.pyyne.entities.PyyneControllerTransaction;
import com.pyyne.entities.PyyneControllerTransactionType;

public class Transaction implements PyyneControllerTransaction {

    private final PyyneControllerTransactionType type;
    PyyneAllowedCurrency currency;
    private PyyneBankAccountMetadata metadata;
    private String description;
    private double amount;

    public Transaction(
            double amount,
            String description,
            PyyneAllowedCurrency currency,
            PyyneControllerTransactionType type,
            PyyneBankAccountMetadata metadata){
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.setAccountMetadata(metadata);
    }
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public PyyneControllerTransactionType getType() {
        return this.type;
    }

    @Override
    public PyyneAllowedCurrency getCurrency() {
        return this.currency;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public PyyneBankAccountMetadata getAccountMetadata() {
        return this.metadata;
    }

    @Override
    public void setAccountMetadata(PyyneBankAccountMetadata metadata) {
        this.metadata = metadata;
    }
}
