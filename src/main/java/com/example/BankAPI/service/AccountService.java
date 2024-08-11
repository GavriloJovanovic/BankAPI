package com.example.BankAPI.service;

import com.example.BankAPI.dto.AccountDTO;
import com.example.BankAPI.model.Account;
import com.example.BankAPI.model.User;
import com.example.BankAPI.repository.AccountRepository;
import com.example.BankAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AccountService handles the business logic for account-related operations.
 * It interacts with the AccountRepository and UserRepository to perform CRUD operations on accounts.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    /**
     * Constructor for AccountService that initializes the repositories.
     *
     * @param accountRepository the repository used for account data access
     * @param userRepository    the repository used for user data access
     */
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new account based on the provided AccountDTO.
     *
     * @param accountDTO the data transfer object containing account information
     * @return the created Account object
     * @throws UserNotFoundException if the user associated with the account is not found
     */
    public Account createAccount(AccountDTO accountDTO) {
        User user = userRepository.findById(accountDTO.getUser_id())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + accountDTO.getUser_id() + " not found"));
        Account newAccount = new Account();
        newAccount.setAccountNumber(accountDTO.getAccountNumber());
        newAccount.setBalance(accountDTO.getBalance());
        newAccount.setUser(user);
        return accountRepository.save(newAccount);
    }

    /**
     * Retrieves all accounts associated with the specified user ID.
     *
     * @param userId the ID of the user whose accounts are to be retrieved
     * @return a list of Account objects associated with the user
     * @throws UserNotFoundException if no accounts are found for the specified user ID
     */
    public List<Account> getAccountsByUserId(Long userId) {
        List<Account> accounts = accountRepository.findByUserId(userId);
        if (accounts.isEmpty()) {
            throw new UserNotFoundException("No accounts found for user ID: " + userId);
        }
        return accounts;
    }
}