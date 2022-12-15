package com.pyyne.challenge.bank.integration;

import com.bank1.integration.Bank1AccountSource;
import com.bank1.integration.Bank1Transaction;
import com.pyyne.challenge.bank.entities.AccountBallance;
import com.pyyne.challenge.bank.entities.Transaction;
import com.pyyne.entities.*;
import com.pyyne.ports.BankSourcesPort;

import java.lang.invoke.SerializedLambda;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class Bank1Source extends Bank1AccountSource implements BankSourcesPort {


    private PyyneControllerTransactionType transactionTypeConversor(int type){
        return type==Bank1Transaction.TYPE_CREDIT ?
                    PyyneControllerTransactionType.CREDIT :
                    PyyneControllerTransactionType.DEBIT;
    }
    @Override
    public List<PyyneControllerTransaction> getTransactions(final PyyneBankAccountMetadata accountMetadata, Date from, Date to) {
        final Long sourceAccountId = Long.parseLong(accountMetadata.getSourceCode());
        final PyyneAllowedCurrency currency = PyyneAllowedCurrency.valueOf(this.getAccountCurrency(sourceAccountId));

        return this.getTransactions(sourceAccountId, from, to)
                .stream()
                .map(
                    item-> new Transaction(
                            item.getAmount(),
                            item.getText(),
                            currency,
                            this.transactionTypeConversor(item.getType()),
                            accountMetadata)
                ).collect(Collectors.toList());

    }

    @Override
    public List<PyyneControllerTransaction> getTransactions(PyyneBankAccountMetadata accountMetadata) {


        Calendar calendar = Calendar.getInstance();
        final Date today = calendar.getTime();

        calendar.add(Calendar.DATE, -1);
        final Date yesterday = calendar.getTime();

        return this.getTransactions(accountMetadata, yesterday, today);

    }

    @Override
    public PyyneControllerAccountBallance getGetBalance(PyyneBankAccountMetadata accountMetadata) {
        final Long sourceAccountId = Long.parseLong(accountMetadata.getSourceCode());
        final PyyneAllowedCurrency currency = PyyneAllowedCurrency.valueOf(this.getAccountCurrency(sourceAccountId));
        return new AccountBallance(currency, this.getAccountBalance(sourceAccountId), accountMetadata);

    }
}