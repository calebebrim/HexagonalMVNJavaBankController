package com.pyyne.challenge.bank.entities;

import com.pyyne.entities.PyyneBankAccountMetadata;

public class BankAccountMetadataImpl implements PyyneBankAccountMetadata {

    protected String name;
    protected String code;
    protected String source;
    protected String sourceCode;
    public BankAccountMetadataImpl(String name, String code, String source, String sourceCode){

        this.name = name;
        this.code = code;
        this.source = source;
        this.sourceCode = sourceCode;

    }

    @Override
    public String getOwnerName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getSourceCode() {
        return this.sourceCode;
    }
}
