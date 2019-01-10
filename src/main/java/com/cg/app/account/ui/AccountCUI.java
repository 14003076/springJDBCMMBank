package com.cg.app.account.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.exception.AccountNotFoundException;
import com.cg.app.account.service.SavingsAccountService;
@Component
public class AccountCUI {
	private static Scanner scanner = new Scanner(System.in);
	@Autowired
	private  SavingsAccountService savingsAccountService; 
	public  void start() throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Savings Account");
			System.out.println("2. Update Account");
			System.out.println("3. Close Account");
			System.out.println("4. Search Account");
			System.out.println("5. Withdraw");
			System.out.println("6. Deposit");
			System.out.println("7. FundTransfer");
			System.out.println("8. Check Current Balance");
			System.out.println("9. Get All Savings Account Details");
			System.out.println("10. Exit");
			System.out.println();
			System.out.println("Make your choice: ");

			int choice = scanner.nextInt();
			performOperation(choice);

		} while (true);
	}

	private  void performOperation(int choice)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		switch (choice) {
		case 1:
			acceptInput("SA");
			break;
		case 2:
			updateAccount();
			break;
		case 3:
			closeAccount();
			break;
		case 4:
			searchAccount();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 8:
			currentBalance();
			break;
		case 9:
			showAllAccounts();
			break;
		case 10:
			/*
			 * try { DBUtil.closeConnection(); } catch (SQLException e) {
			 * e.printStackTrace(); }
			 */
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Choice!");
			break;
		}

	}

	private  void updateAccount() throws ClassNotFoundException,
			SQLException {

		System.out.println("Enter the account number to update: ");
		int accountNumber = scanner.nextInt();
		SavingsAccount savingAccount = null;
		try {

			savingAccount = savingsAccountService.getAccountById(accountNumber);

		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e) {
			e.printStackTrace();

		}
		do {
			System.out.println("Enter which field to update: ");
			System.out.println("1. Account holder name.");
			System.out.println("2. Salary ");
			int choice = scanner.nextInt();
			try {
				updateField(savingAccount, choice);
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

	private void updateField(SavingsAccount savingAccount, int choice)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {

		switch (choice) {
		case 1:
			System.out.println("Enter the name to update: ");
			String updatedName = scanner.next();
			savingAccount.getBankAccount().setAccountHolderName(updatedName);
			savingAccount = savingsAccountService.updateAccount(savingAccount);
			System.out.println(savingAccount.toString());
			break;

		case 2:

			System.out.println("Change the salary field(true/false)");
			boolean updatedSalaryField = scanner.nextBoolean();
			savingAccount.setSalary(updatedSalaryField);

			savingAccount = savingsAccountService.updateAccount(savingAccount);
			System.out.println(savingAccount.toString());
			break;

		case 3:

			start();
			break;

		default:
			System.out.println("Invalid Choice.");
		}
	}

	private  void currentBalance() throws ClassNotFoundException,
			SQLException, AccountNotFoundException {
		System.out.println("Enter the account number to check balance: ");
		int accountNumber = scanner.nextInt();
		SavingsAccount savingsAccount;
		savingsAccount = savingsAccountService.getCurrentBalance(accountNumber);
		String accountHolderName = savingsAccount.getBankAccount()
				.getAccountHolderName();
		double currentBalance = savingsAccount.getBankAccount()
				.getAccountBalance();
		System.out.println("Current Balance of " + accountHolderName + " is "
				+ currentBalance);

	}

	private  void searchAccount() throws ClassNotFoundException,
			SQLException, AccountNotFoundException {
		System.out.println("1.Search By Account Id");
		System.out.println("2.Search By Account Holder Name");
		int search = scanner.nextInt();
		switch (search) {
		case 1:
			System.out.println("Enter account number to search");
			int accountNumber = scanner.nextInt();
			try {
				SavingsAccount savingsAccount = savingsAccountService
						.getAccountById(accountNumber);
				System.out.println(savingsAccount);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("Enter Account Holder  to search");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			try {
				SavingsAccount savingsAccount = savingsAccountService
						.getAccountByName(accountHolderName);
				System.out.println(savingsAccount);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}

	}

	private  void closeAccount() throws ClassNotFoundException,
			SQLException {
		System.out.println("enter account number that you want to delete:");
		int accountNumber = scanner.nextInt();
		savingsAccountService.deleteAccount(accountNumber);
	}

	private  void fundTransfer() {
		System.out.println("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		System.out.println("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		try {
			SavingsAccount senderSavingsAccount = savingsAccountService
					.getAccountById(senderAccountNumber);
			SavingsAccount receiverSavingsAccount = savingsAccountService
					.getAccountById(receiverAccountNumber);
			savingsAccountService.fundTransfer(senderSavingsAccount,
					receiverSavingsAccount, amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  void deposit() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = savingsAccountService
					.getAccountById(accountNumber);
			savingsAccountService.deposit(savingsAccount, amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

	private void withdraw() throws SQLException {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = savingsAccountService
					.getAccountById(accountNumber);
			savingsAccountService.withdraw(savingsAccount, amount);
		} catch (ClassNotFoundException | SQLException
				| AccountNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

	private  void sortMenu(String sortWay) {
		do {
			System.out.println("+++++Ways of Sorting+++++++");
			System.out.println("1. Account Number");
			System.out.println("2. Account Holder Name");
			System.out.println("3. Account Balance");
			System.out.println("4. Exit from Sorting");

			int choice = scanner.nextInt();

		} while (true);
	}

	private  void showAllAccounts() {
		List<SavingsAccount> savingsAccounts;
		try {
			savingsAccounts = savingsAccountService.getAllSavingsAccount();
			for (SavingsAccount savingsAccount : savingsAccounts) {
				System.out.println(savingsAccount);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private  void acceptInput(String type) {
		if (type.equalsIgnoreCase("SA")) {
			System.out.println("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			System.out
					.println("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance = 0.0;
			if (!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			System.out.println("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n") ? false
					: true;
			createSavingsAccount(accountHolderName, accountBalance, salary);
		}
	}

	private  void createSavingsAccount(String accountHolderName,
			double accountBalance, boolean salary) {
		try {
			savingsAccountService.createNewAccount(accountHolderName,
					accountBalance, salary);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
