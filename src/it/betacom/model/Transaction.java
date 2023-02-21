package it.betacom.model;

import java.time.LocalDate;

public class Transaction {
	private Double amount = 0.0;
	private Double balanceState;
	private LocalDate timestamp;

	public Transaction(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	
	public Transaction(Double amount, LocalDate timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalanceState() {
		return balanceState;
	}

	public void setBalanceState(Double balanceState) {
		this.balanceState = balanceState;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
}
