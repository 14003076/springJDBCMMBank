package com.cg.app.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.exception.AccountNotFoundException;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	SavingsAccount deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException;
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	void commit() throws SQLException;
	/*SavingsAccount getAccountBalance(double accountBalance)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;*/
	SavingsAccount getAccountByName(String accountHolderName) throws SQLException, AccountNotFoundException, ClassNotFoundException;
	SavingsAccount getCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
		
	
}
