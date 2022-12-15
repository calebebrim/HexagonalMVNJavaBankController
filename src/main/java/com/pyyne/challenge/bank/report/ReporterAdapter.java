package com.pyyne.challenge.bank.report;

import com.pyyne.entities.PyyneControllerAccountBallance;
import com.pyyne.entities.PyyneControllerTransaction;
import com.pyyne.ports.ReporterPort;

public class ReporterAdapter implements ReporterPort {

    @Override
    public String formatTransaction(PyyneControllerTransaction transaction) {
        return String.format(
            "%s:%s:%.2f:%s:%s:%s",
                transaction.getAccountMetadata().getCode(),
                transaction.getAccountMetadata().getSource(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getType(),
                transaction.getDescription()
        );
    }

    @Override
    public String formatBallance(PyyneControllerAccountBallance balance) {



        return String.format(
                "%s:%s:%.2f:%s",
                balance.getAccountMetadata().getCode(),
                balance.getAccountMetadata().getSource(),
                balance.getAmount(),
                balance.getCurrency()
        );
    }
}
