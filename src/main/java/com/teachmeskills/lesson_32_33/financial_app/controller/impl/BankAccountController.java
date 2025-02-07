package com.teachmeskills.lesson_32_33.financial_app.controller.impl;

import com.teachmeskills.lesson_32_33.financial_app.controller.IBankAccountController;
import com.teachmeskills.lesson_32_33.financial_app.exception.InputValidationException;
import com.teachmeskills.lesson_32_33.financial_app.model.Account;
import com.teachmeskills.lesson_32_33.financial_app.service.AccountService;
import com.teachmeskills.lesson_32_33.financial_app.validation.InputValidator;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

public class BankAccountController implements IBankAccountController {
    private static final Logger logger = Logger.getLogger(BankAccountController.class.getName());
    private final AccountService accountService = new AccountService();
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator = new InputValidator(scanner);

    @Override
    public void start() {
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. View Balance");
            System.out.println("3. Transfer Funds");
            System.out.println("4. Exit");
            System.out.print("Select an action: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> viewBalance();
                case 3 -> transferFunds();
                case 4 -> {
                    logger.info("Exiting the program.");
                    return;
                }
                default -> logger.warning("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void createAccount() {
        try {
            String accountNumber = inputValidator.getAccountNumber ();
            double initialBalance = inputValidator.getInitialBalance();
            String firstName = inputValidator.getFirstName();
            String lastName = inputValidator.getLastName();
            Account account = new Account(0, accountNumber, initialBalance, firstName, lastName, LocalDateTime.now());
            accountService.createAccount(account);
            logger.info("Account created -> " + accountNumber);
        } catch (InputValidationException e) {
            logger.severe("Input validation error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void viewBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        double balance = accountService.getBalance(accountNumber);
        logger.info("Balance for account " + accountNumber + " -> " + balance);
        System.out.println("Balance for account " + accountNumber + ": " + balance);
    }

    @Override
    public void transferFunds() {
        try {
            String fromAccount = inputValidator.getAccountNumber();
            String toAccount = inputValidator.getAccountNumber();
            double amount = inputValidator.getTransferAmount();
            accountService.transferFunds(fromAccount, toAccount, amount);
            logger.info("Transfer completed -> " + amount + " from " + fromAccount + " to " + toAccount);
            System.out.println("Transfer completed successfully.");
        } catch (InputValidationException e) {
            logger.severe("Input validation error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error during fund transfer -> " + e.getMessage());
            System.out.println("Error during fund transfer: " + e.getMessage());
        }
    }
}