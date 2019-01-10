package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.cg.app.account.SavingsAccount;

@Aspect
@Service
public class SavingsAccountValidation {
  Logger logger = Logger.getLogger(SavingsAccountValidation.class.getName());
  @Around("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))")
  public void depositValidation(ProceedingJoinPoint pjp)throws Throwable{
	  Object[] param = pjp.getArgs();
	  SavingsAccount  savingsAccount = (SavingsAccount) param[0];
	  double amount = (double) param[1];
	  if(amount > 0 && savingsAccount!=null) {
		  pjp.proceed();
	  }
	  else if(savingsAccount == null) {
		  logger.info("account number not found");
	  }
	   logger.info("deposit amount should be greater than 0");
  }

@Around("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
public void withdrawValidation(ProceedingJoinPoint pjp)throws Throwable{
	  Object[] param = pjp.getArgs();
	  SavingsAccount  savingsAccount = (SavingsAccount) param[0];
	  if(savingsAccount!=null) {
		  double currentBalance = savingsAccount.getBankAccount().getAccountBalance();
		  double amount = (double) param[1];
		  if(amount > 0 && currentBalance >= amount) {
			  pjp.proceed();
            }
		  logger.info("withdraw amount should be greater than 0");
	  }
	  logger.info("account number not found");
     }

@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.fundtransfer(..))")
public void fundtransferValidation(ProceedingJoinPoint pjp)throws Throwable{
	  Object[] param = pjp.getArgs();
	  SavingsAccount  sender = (SavingsAccount) param[0];
	  System.out.println(sender);
	  double senderBalance = sender.getBankAccount().getAccountBalance();
	  SavingsAccount  receiver = (SavingsAccount) param[1];
	  System.out.println(sender);
	  double receiverBalance = receiver.getBankAccount().getAccountBalance();
	  double amount = (double) param[2];
	  if(sender==null || receiver ==null) {
		  logger.info("check the account number you have entered");
	  }
	  else if(amount <= senderBalance) {
			  pjp.proceed();
            }
		  logger.info("withdraw amount should be greater than 0");
	  }
     }
 
