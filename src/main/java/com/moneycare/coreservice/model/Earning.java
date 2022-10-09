package com.moneycare.coreservice.model;

import java.time.LocalDateTime;
import java.util.Date;

public class  Earning {
   private int amount;
    private LocalDateTime day;
    private BasicUserEntity fromUser;

    public Earning(int amount, LocalDateTime day, BasicUserEntity fromUser) {
        this.amount = amount;
        this.day = day;
        this.fromUser = fromUser;
    }

    public Earning() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public BasicUserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(BasicUserEntity fromUser) {
        this.fromUser = fromUser;
    }
}
