package com.teachmeskills.lesson_32_33.financial_app.util;

public interface Constants {
    String CUSTOMER = "INSERT INTO customer (account_number, balance, first_name, last_name, created_at) VALUES (?, ?, ?, ?, ?)";
    String BALANCE = "SELECT balance FROM customer WHERE account_number = ?";
    String WITHDRAWAL = "UPDATE customer SET balance = balance - ? WHERE account_number = ?";
    String DEPOSIT = "UPDATE customer SET balance = balance + ? WHERE account_number = ?";
    String TRANSACTIONS = "INSERT INTO transactions (account_number, transaction_date, transaction_type, amount) VALUES (?, ?, ?, ?)";
    String TRANSACTION_LOG = "INSERT INTO transaction_log (transaction_id, log_date, log_message) VALUES (?, ?, ?)";

}
