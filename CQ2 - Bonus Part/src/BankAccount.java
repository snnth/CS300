///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           CQ2 - Bonus Part
// Course:          CS 300, Spring 2021
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Persons: None
// Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.ArrayList;

/**
 * Models a general bank account with the ability to make deposits and withdrawls.
 *
 * @author Matthew Smith
 */
public class BankAccount {

	private final static String BANK_NAME = "Fantastic Bank";
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	private static int numberGenerator = 1001;
	protected final int NUMBER;
	private double balance;

	/**
	 * Main method. One call to the demo method. Command-line arguments not supported.
	 *
	 * @param args none
	 */
	public static void main(String[] args) {
		demo();
	}

	/**
	 * Creates a new bank account under the bank "Fantastic Bank" with an initial balance and assigns
	 * it a unique account number. Initial balance must be no less than $10.
	 *
	 * @param initialBalance amount equal to or greater than $10 to initially store in this bank
	 *                       account
	 */
	public BankAccount(double initialBalance) {
		if (initialBalance < 10.0)
			throw new IllegalArgumentException("Initial balance must be no less than $10.00!");

		NUMBER = BankAccount.numberGenerator++;
		balance = initialBalance;
		BankAccount.accounts.add(this);
	}

	/**
	 * Deposits certain amount into this bank account. Amount must be strictly positive.
	 *
	 * @param amount amount, which must be greater than zero, to deposit into this bank account
	 */
	public void deposit(double amount) {
		if (amount < 0.01)
			throw new IllegalArgumentException("Deposit amount must be greater than $0.00!");

		balance += amount;
	}

	/**
	 * Withdraws certain amount from this bank account. Amount must be strictly positive. If amount
	 * to be withdrawn will overdraw the account, no transaction will be made.
	 *
	 * @param amount amount, which be greater than zero and equal to or less than the current balance
	 *               of the account, to withdraw from this bank account
	 * @return true if withdraw is successfully made, false if otherwise
	 */
	public boolean withdraw(double amount) {
		if (amount < 0.01 || (balance - amount < 0.00))
			return false;

		balance -= amount;
		return true;
	}

	/**
	 * Returns the current balance of this account.
	 *
	 * @return balance of this account
	 */
	public double balance() {
		return balance;
	}

	/**
	 * Determines if the object in question is this account.
	 *
	 * @param other object to be compared
	 * @return true if object is equal, false if otherwise
	 */
	public boolean equals(Object other) {
		if (other instanceof BankAccount && ((BankAccount) other).NUMBER == this.NUMBER)
			return true;

		return false;
	}

	/**
	 * Returns a formatted list of all accounts at this bank and their current balances.
	 *
	 * @return formatted list of all accounts at this bank
	 */
	public static String getAllAccounts() {
		String returnString = BankAccount.BANK_NAME + " Accounts\n" +
						"--------------------------------------\n" +
						"| Acc#     |  MnyMkt  |      Balance |\n" +
						"--------------------------------------\n";
		String returnStringFormat = "| %-12d| %s |%16.2f |\n";

		BankAccount currentAccount;
		for (int i = 0; i < BankAccount.accounts.size(); i++) {
			currentAccount = BankAccount.accounts.get(i);
			returnString += String.format(returnStringFormat, currentAccount.NUMBER,
							(currentAccount.getClass().equals(MoneyMarketAccount.class) ? "MM" : "  "),
							BankAccount.accounts.get(i).balance());
		}

		return returnString + "--------------------------------------\n";
	}

	public static void demo() {
		try {
			BankAccount teenagersAccount = new BankAccount(50.0);
			BankAccount fathersAccount = new BankAccount(1000.0);
			BankAccount mothersAccount = new BankAccount(1000.0);
			MoneyMarketAccount fathersMoneyMarket = new MoneyMarketAccount(500.0);
			MoneyMarketAccount mothersMoneyMarket = new MoneyMarketAccount(500.0);

			if (fathersAccount.withdraw(200.0))
				teenagersAccount.deposit(200.0);

			if (mothersAccount.withdraw(200.0))
				teenagersAccount.deposit(200.0);

			teenagersAccount.deposit(50.0);
			teenagersAccount.withdraw(500.0);

			if (fathersMoneyMarket.withdraw(200.0))
				fathersAccount.deposit(200.0);

			if (mothersMoneyMarket.withdraw(200.0))
				mothersAccount.deposit(200.0);

			fathersMoneyMarket.deposit(10000.0);
			mothersMoneyMarket.deposit(10000.0);

			System.out.println(BankAccount.getAllAccounts());
			for (int i = 0; i < BankAccount.accounts.size(); i++) {
				if (BankAccount.accounts.get(i).getClass().equals(MoneyMarketAccount.class))
					((MoneyMarketAccount) BankAccount.accounts.get(i)).addInterest();
			}
			System.out.println(BankAccount.getAllAccounts());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}
