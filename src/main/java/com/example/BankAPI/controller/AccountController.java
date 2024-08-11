package com.example.BankAPI.controller;


import com.example.BankAPI.dto.AccountDTO;
import com.example.BankAPI.model.Account;
import com.example.BankAPI.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AccountController is responsible for handling HTTP requests related to Account operations.
 * It provides endpoints for creating accounts and retrieving accounts by user ID.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Constructor for AccountController that initializes the AccountService.
     *
     * @param accountService the service used to handle account-related operations
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint to create a new account.
     *
     * @param accountDTO the account data transfer object containing account information
     * @return a ResponseEntity containing the created Account object
     */
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(createdAccount);
    }

    /**
     * Endpoint to retrieve all accounts associated with a specific user ID.
     *
     * @param userId the ID of the user whose accounts are to be retrieved
     * @return a ResponseEntity containing a list of Account objects or a 204 No Content status if no accounts found
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build();  // Returning 204 No Content if no accounts found
        }
        return ResponseEntity.ok(accounts);
    }
}