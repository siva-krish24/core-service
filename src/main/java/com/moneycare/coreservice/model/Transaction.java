package com.moneycare.coreservice.model;

public class Transaction {
    public String trnId,trnEmail,trnDate,trnAmount;

    public Transaction(String trnId, String trnEmail, String trnDate, String trnAmount) {
        this.trnId = trnId;
        this.trnEmail = trnEmail;
        this.trnDate = trnDate;
        this.trnAmount = trnAmount;
    }
    public Transaction(String trnEmail, String trnDate, String trnAmount) {
        this.trnEmail = trnEmail;
        this.trnDate = trnDate;
        this.trnAmount = trnAmount;
    }

    public Transaction() {
    }

    public String getTrnId() {
        return trnId;
    }

    public void setTrnId(String trnId) {
        this.trnId = trnId;
    }

    public String getTrnEmail() {
        return trnEmail;
    }

    public void setTrnEmail(String trnEmail) {
        this.trnEmail = trnEmail;
    }

    public String getTrnDate() {
        return trnDate;
    }

    public void setTrnDate(String trnDate) {
        this.trnDate = trnDate;
    }

    public String getTrnAmount() {
        return trnAmount;
    }

    public void setTrnAmount(String trnAmount) {
        this.trnAmount = trnAmount;
    }


}
