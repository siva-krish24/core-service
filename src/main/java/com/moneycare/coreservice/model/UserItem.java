package com.moneycare.coreservice.model;

import java.util.List;
import java.util.Map;

public class UserItem {
    private String userId;
    private String userEmail;
    private int availBalance;
    private int totalEarnings;
    private List<BasicUserEntity> userTeam;
    private Map<String,BankAccount> userBankAccount;

    public UserItem(String userId, String userEmail, int availBalance, int totalEarnings, List<BasicUserEntity> userTeam, Map<String,BankAccount> userBankAccount) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.availBalance = availBalance;
        this.totalEarnings = totalEarnings;
        this.userTeam = userTeam;
        this.userBankAccount = userBankAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(int availBalance) {
        this.availBalance = availBalance;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public List<BasicUserEntity> getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(List<BasicUserEntity> userTeam) {
        this.userTeam = userTeam;
    }

    public Map<String, BankAccount> getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(Map<String, BankAccount> userBankAccount) {
        this.userBankAccount = userBankAccount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\": \"").append(userId).append('\"');
        sb.append(", \"userEmail\": \"").append(userEmail).append('\"');
        sb.append(", \"availBalance\": ").append(availBalance);
        sb.append(", \"totalEarnings\": ").append(totalEarnings);
        sb.append(", \"userTeam\": ").append(userTeam);
        sb.append(", \"userBankAccount\": ").append(userBankAccount);
        sb.append("}");

        return sb.toString();
    }
}
