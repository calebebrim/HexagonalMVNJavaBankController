package com.pyyne.challenge.bank.integration;


import com.bank2.integration.Bank2AccountBalance;
import com.bank2.integration.Bank2AccountSource;
import com.bank2.integration.Bank2AccountTransaction;
import com.pyyne.challenge.bank.entities.AccountBallance;
import com.pyyne.challenge.bank.entities.Transaction;
import com.pyyne.entities.*;
import com.pyyne.ports.BankSourcesPort;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class Bank2Source extends Bank2AccountSource implements BankSourcesPort  {


    private PyyneControllerTransactionType transactionTypeConversor(Bank2AccountTransaction.TRANSACTION_TYPES type){
        return type == Bank2AccountTransaction.TRANSACTION_TYPES.CREDIT? PyyneControllerTransactionType.CREDIT : PyyneControllerTransactionType.DEBIT;
    }
    @Override
    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata, Date from, Date to) {

        return this.getTransactions(Long.parseLong(accountMetadata.getSourceCode()),from, to)
                .stream()
                .map(item -> new Transaction(
                        item.getAmount(),
                        item.getText(),
                        this.getGetBalance(accountMetadata).getCurrency(),
                        this.transactionTypeConversor(item.getType()),
                        accountMetadata
                ))
                .collect(Collectors.toList());

    }

    @Override
    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata) {
        Calendar calendar = Calendar.getInstance();
        final Date today = calendar.getTime();

        calendar.add(Calendar.DATE, -1);
        final Date yesterday = calendar.getTime();

        return this.getTransactions(accountMetadata, yesterday,today);

    }

    @Override
    public PyyneControllerAccountBallance getGetBalance(PyyneBankAccountMetadata accountMetadata) {

        Bank2AccountBalance balance = this.getBalance(Long.parseLong(accountMetadata.getSourceCode()));
        PyyneAllowedCurrency currency = PyyneAllowedCurrency.valueOf(balance.getCurrency());
        return new AccountBallance(currency,balance.getBalance(),accountMetadata);
    }
}