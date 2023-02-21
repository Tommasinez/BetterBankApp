package it.betacom.controller;

import java.time.LocalDate;

import it.betacom.model.Account;
import it.betacom.model.BankAccount;
import it.betacom.model.DepositAccount;

public class Controller {	
	public static void main(String[] args) {		
		System.out.println("BANK ACCOUNT TEST");
		Account bankAccount = new BankAccount("Mario Rossi", LocalDate.of(2021, 2, 12), 1800.0);
		bankAccount.deposit(900.0, LocalDate.of(2021, 8, 15));
		bankAccount.giveInterests(LocalDate.of(2021, 12, 31));
		bankAccount.withdrawal(900.0, LocalDate.of(2022, 7, 01));
		bankAccount.giveInterests(LocalDate.of(2022, 12, 31));
		bankAccount.giveInterests(LocalDate.now());
		
		System.out.println();		
		System.out.println("DEPOSIT ACCOUNT TEST");		
		Account depositAccount = new DepositAccount("Mario Rossi", LocalDate.of(2021, 2, 12), 1800.0);
		depositAccount.giveInterests(LocalDate.of(2021, 12, 31));
		depositAccount.withdrawal(900.0, LocalDate.of(2022, 7, 01));
		depositAccount.giveInterests(LocalDate.of(2022, 12, 31));
		depositAccount.giveInterests(LocalDate.now());
		
//		bankAccount.getStatement().forEach(t -> {
//			System.out.println(t.getAmount());
//			System.out.println(t.getBalanceState());
//			System.out.println(t.getTimestamp());
//		});
	}
}
