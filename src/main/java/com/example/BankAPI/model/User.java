package com.example.BankAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * User class is a class that represent user of the bank
 */
@Data
@Entity
@Table(name = "Users")
public class User {

    /**
     * ID is DB number that is identifying user in the DB
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * firstName is name of the user
     */
    @Column(name = "FirstName", nullable = false)
    private String firstName;

    /**
     * lastName is surname of the user
     */
    @Column(name = "LastName", nullable = false)
    private String lastName;

    /**
     * passwordHash is password of the user that has been changed using encoding function
     */
    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    /**
     * email is email
     */
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    /**
     * account is the list of accounts that one user have
     */
    @JsonIgnoreProperties("user") // Prevent recursion
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
