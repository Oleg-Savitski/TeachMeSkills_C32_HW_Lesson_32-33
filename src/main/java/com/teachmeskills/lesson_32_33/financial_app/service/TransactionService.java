package com.teachmeskills.lesson_32_33.financial_app.service;

import com.teachmeskills.lesson_32_33.financial_app.model.Transaction;
import com.teachmeskills.lesson_32_33.financial_app.util.DatabaseConfig;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.teachmeskills.lesson_32_33.financial_app.util.Constants.TRANSACTIONS;

public class TransactionService {
    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());

    public void Transaction(Transaction transaction) {

        try (Connection conn = DriverManager.getConnection(DatabaseConfig.getInstance().getUrl(),
                DatabaseConfig.getInstance().getUser (),
                DatabaseConfig.getInstance().getPassword());
             PreparedStatement preparedStatement = conn.prepareStatement(TRANSACTIONS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, transaction.getAccountNumber());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(transaction.getTransactionTime()));
            preparedStatement.setString(3, transaction.getTransactionType());
            preparedStatement.setDouble(4, transaction.getAmount());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                transaction.setTransactionId(generatedKeys.getInt(1));
            }

            logger.info("The transaction was recorded successfully -> " + transaction.getTransactionId());

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Error when recording a transaction -> " + e.getMessage(), e);
        }
    }
}