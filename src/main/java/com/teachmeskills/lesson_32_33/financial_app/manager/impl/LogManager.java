package com.teachmeskills.lesson_32_33.financial_app.manager.impl;

import com.teachmeskills.lesson_32_33.financial_app.manager.ILogManager;
import com.teachmeskills.lesson_32_33.financial_app.model.LogTransaction;
import com.teachmeskills.lesson_32_33.financial_app.util.DatabaseConfig;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.teachmeskills.lesson_32_33.financial_app.util.Constants.TRANSACTION_LOG;

public class LogManager implements ILogManager {
    private static final Logger logger = Logger.getLogger(LogManager.class.getName());

    @Override
    public void logTransactionService(LogTransaction logTransaction) {

        try (Connection conn = DriverManager.getConnection(DatabaseConfig.getInstance().getUrl(),
                DatabaseConfig.getInstance().getUser (),
                DatabaseConfig.getInstance().getPassword());
             PreparedStatement preparedStatement = conn.prepareStatement(TRANSACTION_LOG)) {
            preparedStatement.setInt(1, logTransaction.getTransactionId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(logTransaction.getLogTime()));
            preparedStatement.setString(3, logTransaction.getLogMessage());
            preparedStatement.executeUpdate();

            logger.info("Transaction log entry added successfully -> " + logTransaction.getLogMessage());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when writing to the transaction log -> " + e.getMessage(), e);
        }
    }
}