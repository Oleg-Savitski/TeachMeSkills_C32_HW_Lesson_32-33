Task:

Create a Java application that will interact with the database using JDBC.
Your task is to implement a bank account management system that supports transactions.

Requirements:

1. Configuring the database ->
Create an accounts table with the fields:
id (PRIMARY KEY, BIGSERIAL)
account_number (VARCHAR)
balance (DECIMAL)

2. Create an account ->
A method for adding a new account with an initial balance.
Balance View ->
A method to get the current balance by account number.
Transfer of funds ->
A method for transferring funds from one account to another.
This method must be implemented as a transaction to ensure data integrity.

3. Implement the transfer of funds between two accounts as a transaction.
If the transfer cannot be completed (for example, there are insufficient funds),
the transaction must be rolled back.

4. Handle possible exceptions, such as insufficient funds for the transfer,
and ensure that the transaction is rolled back in case of an error.

5. Implement logging of operations in a separate transactions table,
where information about each transfer will be stored ->

transaction_id,
from_account,
to_account,
amount,
timestamp.
