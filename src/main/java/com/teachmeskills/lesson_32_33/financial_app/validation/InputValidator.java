package com.teachmeskills.lesson_32_33.financial_app.validation;

import com.teachmeskills.lesson_32_33.financial_app.exception.InputValidationException;

import java.util.Scanner;

public class InputValidator {

    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getAccountNumber() throws InputValidationException {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accountNumber.isEmpty()) {
            throw new InputValidationException("Account number cannot be empty.");
        }
        return accountNumber;
    }

    public double getInitialBalance() throws InputValidationException {
        System.out.print("Enter initial balance: ");
        double initialBalance;
        try {
            initialBalance = Double.parseDouble(scanner.nextLine());
            if (initialBalance < 0) {
                throw new InputValidationException("Initial balance cannot be negative.");
            }
        } catch (NumberFormatException e) {
            throw new InputValidationException("Invalid input for initial balance.");
        }
        return initialBalance;
    }

    public String getFirstName() throws InputValidationException {
        System.out.print("Enter owner's first name: ");
        String firstName = scanner.nextLine();
        if (firstName.isEmpty()) {
            throw new InputValidationException("First name cannot be empty.");
        }
        return firstName;
    }

    public String getLastName() throws InputValidationException {
        System.out.print("Enter owner's last name: ");
        String lastName = scanner.nextLine();
        if (lastName.isEmpty()) {
            throw new InputValidationException("Last name cannot be empty.");
        }
        return lastName;
    }

    public double getTransferAmount() throws InputValidationException {
        System.out.print("Enter transfer amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new InputValidationException("Transfer amount must be positive.");
            }
        } catch (NumberFormatException e) {
            throw new InputValidationException("Invalid input for transfer amount.");
        }
        return amount;
    }
}