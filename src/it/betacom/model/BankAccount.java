package it.betacom.model;

import java.time.LocalDate;

public class BankAccount extends Account {
	public BankAccount(String holderFullName, LocalDate openingDate, Double balance) {
		super(holderFullName, openingDate, balance);
		interestRate = 0.05;
	}
	
}
