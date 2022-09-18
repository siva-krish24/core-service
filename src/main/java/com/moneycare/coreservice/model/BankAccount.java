package com.moneycare.coreservice.model;
public class BankAccount {
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String accountNo;
    String bankName;
    String ifsc;
    String upiId;
    String name;

    public BankAccount(String accountNo, String bankName, String ifsc, String name) {
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.name = name;
    }

    public BankAccount(String accountNo, String bankName, String ifsc, String upiId, String name) {
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.ifsc = ifsc;
        this.upiId = upiId;
        this.name = name;
    }

    public BankAccount() {
    }
}
