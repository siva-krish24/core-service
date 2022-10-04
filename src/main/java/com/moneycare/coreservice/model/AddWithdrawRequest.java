package com.moneycare.coreservice.model;

public class AddWithdrawRequest {

    public UserAuthEntity srcUser;
    public Transaction userTransaction;

    public AddWithdrawRequest(UserAuthEntity srcUser, Transaction userTransaction) {
        this.srcUser = srcUser;
        this.userTransaction = userTransaction;
    }

    public AddWithdrawRequest() {
    }

    @Override
    public String toString() {
        return "AddWithdrawRequest{" +
                "srcUser=" + srcUser.toString() +
                ", userTransaction=" + userTransaction.toString() +
                '}';
    }
}

