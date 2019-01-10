package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {
	Logger logger = Logger.getLogger(Logging.class.getName());

	@After("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.java.createNewAccount(..))")
	public void createNewAccountLog() {
		logger.info("new Account Has created");
	}

	@After("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.java.deposit(..))")
	public void depositLog() {
		logger.info("amount is deposited");
	}

	@After("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.java.withdraw(..))")
	public void withdrawLog() {
		logger.info("amount is withdrawl");
	}

	@After("execution(*com.cg.app.account.service.SavingsAccountServiceImpl.java.fundTransfer(..))")
	public void fundTransferLog() {
		logger.info("amount is transfered");
	}

}
