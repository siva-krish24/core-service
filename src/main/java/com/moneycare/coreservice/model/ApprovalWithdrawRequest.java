package com.moneycare.coreservice.model;

public class ApprovalWithdrawRequest {

    public UserAuthEntity srcUser;
    public Transaction userTransaction;

    public ApprovalWithdrawRequest(UserAuthEntity srcUser, Transaction userTransaction) {
        this.srcUser = srcUser;
        this.userTransaction = userTransaction;
    }

    public ApprovalWithdrawRequest() {
    }
}
