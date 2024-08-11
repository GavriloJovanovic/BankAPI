package com.example.BankAPI.dto;

/**
 * AccountDTO is a Data Transfer Object used to encapsulate account-related data.
 */
public class AccountDTO {
    private int id;  // Optional based on use case
    private String accountNumber;
    private double balance;
    private long user_id;

// Getters and setters

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
