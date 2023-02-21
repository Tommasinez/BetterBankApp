package it.betacom.model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Account {
	DecimalFormat decimal = new DecimalFormat("0.00");
	private String holderFullName;
	private LocalDate openingDate;
	private Double balance = 0.0;
	private List<Transaction> statement = new ArrayList<>();
	protected Double interestRate;

	public Account(String holderFullName, LocalDate openingDate, Double balance) {
		this.holderFullName = holderFullName;
		this.openingDate = openingDate;
		deposit(balance, openingDate);
	}

	public void deposit(Double amount, LocalDate timestamp) {
		Transaction newTransaction = new Transaction(amount, timestamp);

		setBalance(getBalance() + amount);

		newTransaction.setBalanceState(getBalance());

		getStatement().add(newTransaction);

		System.out.println("Operation done!");
		System.out.println("Amount: €" + decimal.format(newTransaction.getAmount()));
		System.out.println("Balance after deposit: €" + decimal.format(getBalance()));
		System.out.println();
	}

	public void withdrawal(Double amount, LocalDate timestamp) {
		Transaction newTransaction = new Transaction(amount, timestamp);

		if (getBalance() >= amount) {
			setBalance(getBalance() - amount);

			newTransaction.setBalanceState(getBalance());

			getStatement().add(newTransaction);

			System.out.println("Operation done!");
			System.out.println("Amount: €" + decimal.format(newTransaction.getAmount()));
			System.out.println("Balance after withdrawal: €" + decimal.format(getBalance()));
			System.out.println();
		} else {
			System.out.println("Transaction failed! Your balance is less than €" + amount);
			System.out.println("Actual balance: €" + getBalance());
			System.out.println();
		}
	}

	public void giveInterests(LocalDate timestamp) {
		Transaction newTransaction = new Transaction(timestamp);
		Transaction newTransactionCopy = new Transaction(timestamp.plusDays(1));

		List<Transaction> yearlyStatement = getStatement().stream()
				.filter(element -> element.getTimestamp().getYear() == timestamp.getYear())
				.collect(Collectors.toList());

		for (Integer i = 0; i < yearlyStatement.size(); i++) {
			Transaction lastTransaction = yearlyStatement.get(yearlyStatement.size() - 1);
			Long daysPriorToPreviousTransaction;
			if (yearlyStatement.get(i) == lastTransaction) {
				daysPriorToPreviousTransaction = ChronoUnit.DAYS.between(yearlyStatement.get(i).getTimestamp(),
						timestamp);
			} else {
				daysPriorToPreviousTransaction = ChronoUnit.DAYS.between(yearlyStatement.get(i).getTimestamp(),
						yearlyStatement.get(i + 1).getTimestamp());
			}

			Double interest = ((yearlyStatement.get(i).getBalanceState() * interestRate) / 365) * daysPriorToPreviousTransaction;
			setBalance(getBalance() + interest);

			newTransaction.setAmount(interest);
			newTransaction.setBalanceState(getBalance());

			newTransactionCopy.setAmount(interest);
			newTransactionCopy.setBalanceState(getBalance());

			System.out.println("Operation done!");
			System.out.println("Amount: €" + decimal.format(newTransaction.getAmount()));
			System.out.println("Your balance after interests: €" + decimal.format(getBalance()));
			System.out.println();
		}

		getStatement().add(newTransaction);
		getStatement().add(newTransactionCopy);
	}

	public String getHolderFullName() {
		return holderFullName;
	}

	public void setHolderFullName(String holderFullName) {
		this.holderFullName = holderFullName;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Transaction> getStatement() {
		return statement;
	}

	public void setStatement(List<Transaction> statement) {
		this.statement = statement;
	}
}
