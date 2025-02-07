package com.teachmeskills.lesson_32_33.financial_app.service;

import com.teachmeskills.lesson_32_33.financial_app.exception.AccountValidationException;
import com.teachmeskills.lesson_32_33.financial_app.manager.impl.LogManager;
import com.teachmeskills.lesson_32_33.financial_app.model.Account;
import com.teachmeskills.lesson_32_33.financial_app.model.Transaction;
import com.teachmeskills.lesson_32_33.financial_app.model.LogTransaction;
import com.teachmeskills.lesson_32_33.financial_app.util.DatabaseConfig;
import com.teachmeskills.lesson_32_33.financial_app.validation.AccountValidator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.teachmeskills.lesson_32_33.financial_app.util.Constants.*;

public class AccountService {
    private static final Logger logger = Logger.getLogger(AccountService.class.getName());
    private final TransactionService transactionService = new TransactionService();
    private final LogManager logManager = new LogManager();
    private final AccountValidator accountValidator;
    private Connection conn;

    public AccountService() {
        this.accountValidator = new AccountValidator(this);
    }


    public void createAccount(Account account) {
        try (Connection conn = DriverManager.getConnection(DatabaseConfig.getInstance().getUrl(),
                DatabaseConfig.getInstance().getUser (),
                DatabaseConfig.getInstance().getPassword());
             PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER)) {
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setDouble(2, account.getBalance());
            preparedStatement.setString(3, account.getFirstName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(account.getCreatedAt()));
            preparedStatement.executeUpdate();
            logger.info("Account created successfully: " + account.getAccountNumber());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while creating account: " + e.getMessage(), e);
        }
    }

    public double getBalance(String accountNumber) {
        try (Connection conn = DriverManager.getConnection(DatabaseConfig.getInstance().getUrl(),
                DatabaseConfig.getInstance().getUser (),
                DatabaseConfig.getInstance().getPassword());
             PreparedStatement preparedStatement = conn.prepareStatement(BALANCE)) {
            preparedStatement.setString(1, accountNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            } else {
                logger.warning("Account not found: " + accountNumber);
                return 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error while retrieving balance: " + e.getMessage(), e);
            return 0;
        }
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) {
        try {
            accountValidator.validateTransfer(fromAccount, toAccount, amount);
        } catch (AccountValidationException e) {
            logger.warning("Error: " + e.getMessage());
            throw e;
        }

        Transaction transaction = new Transaction(0, fromAccount, LocalDateTime.now(), "Transfer", amount);
        LogTransaction logTransaction = new LogTransaction(0, 0, LocalDateTime.now(), "");

        try (Connection connection = DriverManager.getConnection(DatabaseConfig.getInstance().getUrl(),
                DatabaseConfig.getInstance().getUser (),
                DatabaseConfig.getInstance().getPassword())) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(WITHDRAWAL)) {
                preparedStatement.setDouble(1, amount);
                preparedStatement.setString(2, fromAccount);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Failed to withdraw funds from account: " + fromAccount);
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(DEPOSIT)) {
                preparedStatement.setDouble(1, amount);
                preparedStatement.setString(2, toAccount);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Failed to deposit funds into account: " + toAccount);
                }
            }

            transactionService.Transaction(transaction);

            logTransaction.setTransactionId(transaction.getTransactionId());
            logTransaction.setLogMessage("Transfer " + amount + " from " + fromAccount + " to " + toAccount);
            logManager.logTransactionService(logTransaction );

            connection.commit();

            logger.info("Transfer completed successfully: " + amount + " from " + fromAccount + " to " + toAccount);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error during fund transfer: " + e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                logger.log(Level.SEVERE, "Error during transaction rollback: " + rollbackEx.getMessage(), rollbackEx);
            }
        } catch (IllegalArgumentException e) {
            logger.warning("Error: " + e.getMessage());
            throw e;
        }
    }
}