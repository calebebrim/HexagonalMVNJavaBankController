package com.pyyne.ports;

public interface PortAddapterFactory {
    public BankAccountsControllerPort getBankAccountControllerAdapter();

    public BankSourcesPort getBankSourcesAdapter();

    public ReporterPort getReporterAdapter();
}
