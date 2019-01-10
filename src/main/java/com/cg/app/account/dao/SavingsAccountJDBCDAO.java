package com.cg.app.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cg.app.account.dao.*;
import com.cg.app.account.SavingsAccount;
import com.cg.app.account.exception.AccountNotFoundException;
@Repository
@Primary
public class SavingsAccountJDBCDAO implements SavingsAccountDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Object accountBalance;

	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("insert into account values(?,?,?,?,?,?)",
				new Object[] {account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountBalance(),
						account.isSalary(),
						null,"SA"});
		return  account;
		
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("UPDATE account set account_id=?,account_hn=?,account_bal=?,salary=?,od_limit=?,account_type=? WHERE account_id=?",
				new Object[] {account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(),
						account.getBankAccount().getAccountBalance(),
						account.isSalary(),
						null,"SA",
						account.getBankAccount().getAccountNumber()});
		return account;
		
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		return jdbcTemplate.queryForObject("SELECT * FROM account where account_id=?",new Object[] {accountNumber},new SavingsAccountJdbcMapper());
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws SQLException, ClassNotFoundException {
		jdbcTemplate.update("DELETE  FROM account where account_id=?",accountNumber);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		
		return  jdbcTemplate.query("select * from account",new SavingsAccountJdbcMapper());
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
	   jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?", new Object[] {currentBalance,accountNumber});
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
	
	}

	@Override
	public SavingsAccount getAccountByName(String accountHolderName)
			throws SQLException, AccountNotFoundException, ClassNotFoundException {
		
		return jdbcTemplate.queryForObject("SELECT * FROM account where account_hn=?", new Object[] {accountHolderName},new SavingsAccountJdbcMapper());
	}

	@Override
	public SavingsAccount getCurrentBalance(int accountNumber)throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
	SavingsAccount account= jdbcTemplate.queryForObject("select * from account where account_id=?",new Object[] {accountNumber},new SavingsAccountJdbcMapper() ) ;
	return account;
	}

}
