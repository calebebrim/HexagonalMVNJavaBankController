package com.pyyne.challenge.bank;

import com.pyyne.entities.PyyneControllerAccountBallance;
import com.pyyne.entities.PyyneControllerTransaction;
import com.pyyne.ports.BankAccountsControllerPort;
import com.pyyne.ports.BankSourcesPort;
import com.pyyne.ports.PortAddapterFactory;
import com.pyyne.ports.ReporterPort;

/**
 * Controller that pulls information form multiple bank integrations and prints them to the console.
 *
 * Created by Par Renyard on 5/12/21.
 */
public class BankController {
    BankAccountsControllerPort bankAccounts;
    BankSourcesPort bankSources;
    ReporterPort reporter;
    public BankController(PortAddapterFactory factory){
        this.bankAccounts = factory.getBankAccountControllerAdapter();
        this.bankSources = factory.getBankSourcesAdapter();
        this.reporter = factory.getReporterAdapter();
    }

    public String printBalances() {
        System.out.println("Implement me to pull balance information from all available bank integrations and display them, one after the other.");
        StringBuilder reportBuilder = new StringBuilder();
        for(String code :bankAccounts.getAccountsCode()){
            PyyneControllerAccountBallance ballance = bankSources.getGetBalance(bankAccounts.getAccount(code));
            reportBuilder.append(reporter.formatBallance(ballance));
            reportBuilder.append("\n");
        }
        String report = reportBuilder.toString();
        System.out.println(report);
        return report;
    }

    public String printTransactions() {
        System.out.println("Implement me to pull transactions from all available bank integrations and display them, one after the other.");
        StringBuilder reportBuilder = new StringBuilder();
        for(String code :bankAccounts.getAccountsCode()){
            for(PyyneControllerTransaction transaction: bankSources.getTransactions(bankAccounts.getAccount(code))) {
                reportBuilder.append(reporter.formatTransaction(transaction));
                reportBuilder.append("\n");
            }
        }

        String report = reportBuilder.toString();
        System.out.println(report);
        return report;
    }

    public void registerBankAccountsController(BankAccountsControllerPort adapter) {
        bankAccounts = adapter;
    }

    public void registerBankSourcesAdapter(BankSourcesPort adapter){
        bankSources = adapter;
    }
}
