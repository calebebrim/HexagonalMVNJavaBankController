package com.pyyne.challenge.bank;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.pyyne.challenge.bank.entities.BankAccountMetadataImpl;
import com.pyyne.challenge.bank.integration.BankSourceAdapter;
import com.pyyne.challenge.bank.report.ReporterAdapter;
import com.pyyne.entities.PyyneBankAccountMetadata;
import com.pyyne.ports.BankAccountsControllerPort;
import com.pyyne.ports.BankSourcesPort;
import com.pyyne.ports.PortAddapterFactory;
import com.pyyne.ports.ReporterPort;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




class BanksAccountsControllerAdapter implements BankAccountsControllerPort{

    HashMap<String, PyyneBankAccountMetadata> localstorage = new HashMap<String, PyyneBankAccountMetadata>();

    @Override
    public void registerBankAccountMetadata(PyyneBankAccountMetadata accountMetadata) {
        localstorage.put(accountMetadata.getCode(), accountMetadata);
    }

    @Override
    public List<String> getAccountsCode() {
        return new ArrayList<String>(localstorage.keySet());
    }

    @Override
    public PyyneBankAccountMetadata getAccount(String code) {
        return localstorage.get(code);
    }


}

class AdapterFactory implements PortAddapterFactory {
    BankAccountsControllerPort banksAccountsCountroler = new BanksAccountsControllerAdapter();

    @Override
    public BankAccountsControllerPort getBankAccountControllerAdapter() {
        return this.banksAccountsCountroler;
    }

    @Override
    public BankSourcesPort getBankSourcesAdapter() {
        return new BankSourceAdapter();
    }

    @Override
    public ReporterPort getReporterAdapter() {
        return new ReporterAdapter();
    }
}

/**
 * Unit test for simple App.
 */
public class BankControllerTest
{

    BankController controller;
    BanksAccountsControllerAdapter banksAccounts;
    AdapterFactory factory = new AdapterFactory();
    @Before
    public void setUp(){

        this.controller = new BankController(this.factory);
        banksAccounts = (BanksAccountsControllerAdapter) this.factory.getBankAccountControllerAdapter();
        banksAccounts.registerBankAccountMetadata(new BankAccountMetadataImpl("Owner1", "001", "bank1","000000001"));
        banksAccounts.registerBankAccountMetadata(new BankAccountMetadataImpl("Owner2", "002", "bank2", "000000002"));

        controller.registerBankAccountsController(banksAccounts);
    }


    @Test
    public void testPrintBallances()
    {
        String report = controller.printBalances();

        StringBuilder expectedReport = new StringBuilder();
        expectedReport.append("001:bank1:215,50:USD\n");
        expectedReport.append("002:bank2:512,50:USD\n");

        assertEquals(report ,expectedReport.toString());
    }

    @Test
    public void printTransactions()
    {
        String report = controller.printTransactions();


        StringBuilder expectedReportBuilder = new StringBuilder();
        expectedReportBuilder.append("001:bank1:100,00:USD:CREDIT:Check deposit\n");
        expectedReportBuilder.append("001:bank1:25,50:USD:DEBIT:Debit card purchase\n");
        expectedReportBuilder.append("001:bank1:225,00:USD:DEBIT:Rent payment\n");

        expectedReportBuilder.append("002:bank2:125,00:USD:DEBIT:Amazon.com\n");
        expectedReportBuilder.append("002:bank2:500,00:USD:DEBIT:Car insurance\n");
        expectedReportBuilder.append("002:bank2:800,00:USD:CREDIT:Salary\n");
        String expected = expectedReportBuilder.toString();
        assertEquals(report, expected);
    }
}


