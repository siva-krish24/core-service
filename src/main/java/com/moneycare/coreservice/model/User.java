package com.moneycare.coreservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String id;
    private String name;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String firstName;
    private String lastName;
    private Map<String, BankAccount> bankAccounts;

    private List<BasicUserEntity> team;

    public List<BasicUserEntity> getTeam() {
        return team;
    }

    public void setTeam(List<BasicUserEntity> team) {
        this.team = team;
    }

    private List<Earning> earnings;

    private List<Transaction> transactions;
    private int totalEarning;

    private int availBalance;
    private String email;
    private String mobileNo;

    public User(BasicUserEntity basicUser){
        this.firstName = basicUser.getFirstName();
        this.lastName = basicUser.getLastName();
        this.email = basicUser.getEmail();
        this.mobileNo = basicUser.getMobileNo();
    }
     public User(String firstName, String lastName, String email, String mobileNo) {
         this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.bankAccounts = new HashMap<>();
        this.earnings = new ArrayList<>();
        this.team = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, BankAccount> getBankAccounts() {
       return bankAccounts;
    }

    public void setBankAccounts(Map<String, BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public int getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(int availBalance) {
        this.availBalance = availBalance;
    }

    public int getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(int totalEarning) {
        this.totalEarning = totalEarning;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
