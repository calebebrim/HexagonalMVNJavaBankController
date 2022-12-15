package com.pyyne.entities;


public interface PyyneControllerTransaction extends PyyneControllerValue {

    public String getDescription();
    public PyyneControllerTransactionType getType();

}
