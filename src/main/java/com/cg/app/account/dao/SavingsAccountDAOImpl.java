package com.cg.app.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.exception.AccountNotFoundException;
@Repository
public abstract  class SavingsAccountDAOImpl implements SavingsAccountDAO {

	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		return account;
		/*
		 * public List<SavingsAccount> getAllSavingsAccount() throws
		 * ClassNotFoundException, SQLException { List<SavingsAccount> savingsAccounts =
		 * new ArrayList<SavingsAccount>(); Connection connection = getConnection();
		 * Statement statement = connection.createStatement(); ResultSet resultSet =
		 * statement.executeQuery("SELECT * FROM ACCOUNT"); while (resultSet.next()) {//
		 * Check if row(s) is present in table int accountNumber = resultSet.getInt(1);
		 * String accountHolderName = resultSet.getString("account_hn"); double
		 * accountBalance = resultSet.getDouble(3); boolean salary =
		 * resultSet.getBoolean("salary"); SavingsAccount savingsAccount = new
		 * SavingsAccount(accountNumber, accountHolderName, accountBalance, salary);
		 * savingsAccounts.add(savingsAccount); } return savingsAccounts;
		 */
	}
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		/*
		 * //Connection connection = DBUtil.getConnection();
		 * connection.setAutoCommit(false); PreparedStatement preparedStatement =
		 * connection.prepareStatement
		 * ("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		 * preparedStatement.setDouble(1, currentBalance); preparedStatement.setInt(2,
		 * accountNumber); preparedStatement.executeUpdate();
		 */
	}
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return null;
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection.prepareStatement
		 * ("SELECT * FROM account where account_id=?"); preparedStatement.setInt(1,
		 * accountNumber); ResultSet resultSet = preparedStatement.executeQuery();
		 * SavingsAccount savingsAccount = null; if(resultSet.next()) { String
		 * accountHolderName = resultSet.getString("account_hn"); double accountBalance
		 * = resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * savingsAccount = new SavingsAccount(accountNumber, accountHolderName,
		 * accountBalance, salary);
		 * System.out.println("account with accountNumber is exists"); return
		 * savingsAccount; } throw new
		 * AccountNotFoundException("Account with account number "
		 * +accountNumber+" does not exist.");
		 */
	}
	public SavingsAccount getAccountByName(String accountHolderName) throws SQLException, AccountNotFoundException, ClassNotFoundException {
		return null;
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection.prepareStatement
		 * ("SELECT * FROM account where account_hn=?"); preparedStatement.setString(1,
		 * accountHolderName); ResultSet resultSet = preparedStatement.executeQuery();
		 * SavingsAccount savingsAccount = null; if(resultSet.next()) { int accountId =
		 * resultSet.getInt("account_id"); double accountBalance =
		 * resultSet.getDouble(3); boolean salary = resultSet.getBoolean("salary");
		 * savingsAccount = new SavingsAccount(accountId, accountHolderName,
		 * accountBalance,salary); return savingsAccount; } throw new
		 * AccountNotFoundException("Account with account HolderName "
		 * +accountHolderName+" does not exist.");
		 */
		}

	public SavingsAccount deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException {
		return null;
		/*
		 * Connection connection = DBUtil.getConnection();
		 * connection.setAutoCommit(false); PreparedStatement preparedStatement =
		 * connection.prepareStatement ("DELETE  FROM account where account_id=?");
		 * preparedStatement.setInt(1, accountNumber);
		 * preparedStatement.executeUpdate(); return null;
		 */
		
	}

	public void commit() throws SQLException {

	}

	public SavingsAccount updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		return account;
		/*
		 * Connection connection = DBUtil.getConnection(); PreparedStatement
		 * preparedStatement = connection.
		 * prepareStatement("UPDATE ACCOUNT SET account_id = ?, account_hn = ?, account_bal = ?,salary = ?,od_limit = ?,account_type = ? WHERE account_id = ?"
		 * ); preparedStatement.setInt(1, account.getBankAccount().getAccountNumber());
		 * preparedStatement.setString(2,
		 * account.getBankAccount().getAccountHolderName());
		 * preparedStatement.setDouble(3, account.getBankAccount().getAccountBalance());
		 * preparedStatement.setBoolean(4, account.isSalary());
		 * preparedStatement.setObject(5,null ); preparedStatement.setString(6, "SA");
		 * preparedStatement.setInt(7, account.getBankAccount().getAccountNumber());
		 * preparedStatement.executeUpdate(); preparedStatement.close();
		 * 
		 * return account;
		 */
	
	}

	public SavingsAccount getCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		// TODO Auto-generated method stub
		SavingsAccount savingsAccount = getAccountById(accountNumber);
		return savingsAccount;

	}	
}

