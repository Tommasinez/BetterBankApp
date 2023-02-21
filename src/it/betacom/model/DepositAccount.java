package it.betacom.model;

import java.time.LocalDate;

public class DepositAccount extends Account{	
	public DepositAccount(String holderFullName, LocalDate openingDate, Double balance) {
		super(holderFullName, openingDate, balance);
		interestRate = 0.10;
	}
	
	public void withdrawal(Double amount, LocalDate timestamp) {
		if (amount < 1000) {
			super.withdrawal(amount, timestamp);
		} else {
			System.out.println("Sorry, you cannot withdraw more than €1000.");
		}
	}

}
