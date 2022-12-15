package com.pyyne.ports;


import com.pyyne.entities.PyyneBankAccountMetadata;

import java.util.List;

//Responsible for controlling local metadata for bank accounts
public interface BankAccountsControllerPort {

    public void registerBankAccountMetadata(PyyneBankAccountMetadata accountMetadata);

    public List<String> getAccountsCode();

    public PyyneBankAccountMetadata getAccount(String code);
}
