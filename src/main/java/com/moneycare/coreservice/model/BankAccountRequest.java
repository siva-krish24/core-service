package com.moneycare.coreservice.model;

public class BankAccountRequest {
    public UserAuthEntity srcUser;
    public BankAccount bankAccount;

    public BankAccountRequest(UserAuthEntity srcUser, BankAccount bankAccount) {
        this.srcUser = srcUser;
        this.bankAccount = bankAccount;
    }

    public BankAccountRequest() {
    }
}
