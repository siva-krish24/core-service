package com.moneycare.coreservice.model;

import java.util.Date;

public class  Earning {
   private int amount;
    private Date day;
    private User fromUser;

    public Earning(int amount, Date day, User fromUser) {
        this.amount = amount;
        this.day = day;
        this.fromUser = fromUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
