package com.example.BankAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;

/**
 * This is a class of accounts
 * One account is attached to one user
 */
@Data
@Entity
@Table(name = "Accounts")
public class Account {

    /**
     * ID of account is it's unique number that is key in the DB
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * accountNumber of account is it's unique string number that is identifier of account
     */
    @Column(name = "AccountNumber", nullable = false, unique = true)
    private String accountNumber;

    /**
     * balance is an amount of money in euros that user have
     */
    @Column(name = "Balance",nullable = false)
    private double balance;

    /**
     * user is a object that is connected to the account
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}