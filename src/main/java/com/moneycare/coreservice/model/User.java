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

    private int termAmount;

    public int getTermAmount() {
        return termAmount;
    }

    public void setTermAmount(int termAmount) {
        this.termAmount = termAmount;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    private String firstName;
    private String lastName;
    private Map<String, BankAccount> bankAccounts;

    private List<BasicUserEntity> team;

    public List<BasicUserEntity> getTeam() {
        return team;
    }

    public User setTeam(List<BasicUserEntity> team) {
        this.team = team;
        return this;
    }

    private List<Earning> earnings;

    private List<Transaction> transactions;
    private int totalEarning;

    private int availBalance;
    private String email;
    private String mobileNo;

    public User() {
    }

    public User(BasicUserEntity basicUser){
        this.firstName = basicUser.getFirstName();
        this.lastName = basicUser.getLastName();
        this.email = basicUser.getEmail();
        this.mobileNo = basicUser.getMobileNo();
        this.earnings = new ArrayList<>();
        this.team = new ArrayList<>();
        this.transactions = new ArrayList<>();
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

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Map<String, BankAccount> getBankAccounts() {
       return bankAccounts;
    }

    public User setBankAccounts(Map<String, BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
        return this;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public User setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
        return this;
    }

    public int getAvailBalance() {
        return availBalance;
    }

    public User setAvailBalance(int availBalance) {
        this.availBalance = availBalance;
        return this;
    }

    public int getTotalEarning() {
        return totalEarning;
    }

    public User setTotalEarning(int totalEarning) {
        this.totalEarning = totalEarning;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public User setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public User setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }
}
