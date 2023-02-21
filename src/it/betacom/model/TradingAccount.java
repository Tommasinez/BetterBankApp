package it.betacom.model;

import java.time.LocalDate;
import java.util.Random;

public class TradingAccount extends Account {
	Random random = new Random();

	public TradingAccount(String holderFullName, LocalDate openingDate, Double balance) {
		super(holderFullName, openingDate, balance);
		interestRate = 0.10;
	}

	public void openTrade() {
		Transaction transaction = new Transaction(LocalDate.now());
		Double interest = getBalance() * random.nextDouble(-1, 1);
		
		setBalance(getBalance() + interest);
		transaction.setAmount(interest);

		System.out.println("Operation done!");
		System.out.println("Your balance after trade: €" + decimal.format(getBalance()));
		getStatement().add(transaction);
	}
}